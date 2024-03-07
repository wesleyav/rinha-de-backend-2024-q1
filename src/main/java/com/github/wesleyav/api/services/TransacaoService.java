package com.github.wesleyav.api.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.github.wesleyav.api.dtos.requests.TransacaoRequestDTO;
import com.github.wesleyav.api.dtos.responses.ClienteResponseDTO;
import com.github.wesleyav.api.entities.Cliente;
import com.github.wesleyav.api.entities.Transacao;
import com.github.wesleyav.api.repositories.ClienteRepository;
import com.github.wesleyav.api.repositories.TransacaoRepository;
import com.github.wesleyav.api.services.exceptions.ClienteNotFoundException;
import com.github.wesleyav.api.services.exceptions.DebitoCausaSaldoNegativoException;
import com.github.wesleyav.api.services.exceptions.DebitoExcedeLimiteException;
import com.github.wesleyav.api.services.exceptions.ResourceNotFoundException;
import com.github.wesleyav.api.services.exceptions.TipoTransacaoInvalidoException;
import com.github.wesleyav.api.services.exceptions.ValorTransacaoNuloException;
import com.github.wesleyav.api.services.exceptions.ValorTransacaoPositivoException;

import jakarta.transaction.Transactional;

@Service
public class TransacaoService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Transactional
	public Transacao findById(Integer transacaoId) {
		return transacaoRepository.findById(transacaoId).orElseThrow(() -> new ResourceNotFoundException(transacaoId));
	}

	@Transactional
	public List<Transacao> findAll() {
		List<Transacao> transacoes = transacaoRepository.findAll();
		return transacoes;
	}

	@Transactional
	public ClienteResponseDTO transacao(Integer idCliente, TransacaoRequestDTO transacaoRequestDTO) {

		if (transacaoRequestDTO.valor() < 0) {
			throw new ValorTransacaoPositivoException();
		}

		if (transacaoRequestDTO.valor() == null) {
			throw new ValorTransacaoNuloException();
		}

		if (!transacaoRequestDTO.tipo().equalsIgnoreCase("d") && !transacaoRequestDTO.tipo().equalsIgnoreCase("c")) {
			throw new TipoTransacaoInvalidoException();
		}

		if (transacaoRequestDTO.descricao() == null) {
			throw new NullPointerException("Descricao da transacao e nula");
		}

		if (transacaoRequestDTO.descricao().length() > 10) {
			throw new DataIntegrityViolationException("Descricao da transacao excede o tamanho máximo permitido");
		}

		Cliente cliente = clienteRepository.findClienteById(idCliente)
				.orElseThrow(() -> new ClienteNotFoundException("Cliente nao encontrado"));

		if (transacaoRequestDTO.tipo().equalsIgnoreCase("c")) {
			cliente.realizarCredito(transacaoRequestDTO.valor());
		}
		if (transacaoRequestDTO.tipo().equalsIgnoreCase("d")) {
			if (transacaoRequestDTO.valor() > (cliente.getSaldo() + cliente.getLimite())) {
				throw new DebitoExcedeLimiteException();
			}
			if (cliente.getSaldo() < transacaoRequestDTO.valor()) {
				throw new DebitoCausaSaldoNegativoException();
			}
			cliente.realizarDebito(transacaoRequestDTO.valor());
		}

		Transacao transacao = new Transacao();
		transacao.setValor(transacaoRequestDTO.valor());
		transacao.setTipo(transacaoRequestDTO.tipo());
		transacao.setDescricao(transacaoRequestDTO.descricao());
		transacao.setRealizadaEm(Instant.now());
		transacao.setCliente(cliente);

		clienteRepository.save(cliente);

		transacaoRepository.save(transacao);

		ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente.getLimite(), cliente.getSaldo());

		return clienteResponseDTO;
	}
}
