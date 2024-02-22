package com.github.wesleyav.api.services;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.wesleyav.api.dtos.requests.TransacaoRequestDTO;
import com.github.wesleyav.api.dtos.responses.ClienteResponseDTO;
import com.github.wesleyav.api.entities.Cliente;
import com.github.wesleyav.api.entities.Transacao;
import com.github.wesleyav.api.repositories.ClienteRepository;
import com.github.wesleyav.api.repositories.TransacaoRepository;
import com.github.wesleyav.api.services.exceptions.DebitoCausaSaldoNegativoException;
import com.github.wesleyav.api.services.exceptions.DebitoExcedeLimiteException;
import com.github.wesleyav.api.services.exceptions.DescricaoTransacaoInvalidaException;
import com.github.wesleyav.api.services.exceptions.ResourceNotFoundException;
import com.github.wesleyav.api.services.exceptions.TipoTransacaoInvalidoException;
import com.github.wesleyav.api.services.exceptions.ValorTransacaoPositivoException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TransacaoService {

	private final ClienteRepository clienteRepository;
	private final TransacaoRepository transacaoRepository;

	public TransacaoService(ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
		this.clienteRepository = clienteRepository;
		this.transacaoRepository = transacaoRepository;
	}

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
	public ClienteResponseDTO criarTransacao(Integer clienteId, TransacaoRequestDTO transacaoRequestDTO) {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID: " + clienteId));

		validarTransacao(transacaoRequestDTO);

		Transacao transacao = new Transacao();
		transacao.setValor(transacaoRequestDTO.getValor());
		transacao.setTipo(transacaoRequestDTO.getTipo());
		transacao.setDescricao(transacaoRequestDTO.getDescricao());
		transacao.setRealizadaEm(Instant.now());
		transacao.setCliente(cliente);

		transacaoRepository.save(transacao);

		atualizarSaldoCliente(cliente, transacaoRequestDTO);

		clienteRepository.save(cliente);

		ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
		clienteResponseDTO.setLimite(cliente.getLimite());
		clienteResponseDTO.setSaldo(cliente.getSaldo());

		return clienteResponseDTO;
	}

	private void validarTransacao(TransacaoRequestDTO transacaoRequestDTO) {
		if (transacaoRequestDTO.getValor() <= 0) {
			throw new ValorTransacaoPositivoException();
		}

		char tipo = transacaoRequestDTO.getTipo();
		if (tipo != 'c' && tipo != 'd') {
			throw new TipoTransacaoInvalidoException();
		}

		String descricao = transacaoRequestDTO.getDescricao();
		if (descricao == null || descricao.length() < 1 || descricao.length() > 10) {
			throw new DescricaoTransacaoInvalidaException();
		}
	}

	private void atualizarSaldoCliente(Cliente cliente, TransacaoRequestDTO transacao) {
		if (transacao.getTipo() == 'd' && transacao.getValor() > (cliente.getSaldo() + cliente.getLimite())) {
			throw new DebitoExcedeLimiteException();
		}

		if (transacao.getTipo() == 'd' && cliente.getSaldo() < transacao.getValor()) {
			throw new DebitoCausaSaldoNegativoException();
		}

		if (transacao.getTipo() == 'c') {
			cliente.setSaldo(cliente.getSaldo() + transacao.getValor());
		} else if (transacao.getTipo() == 'd') {
			cliente.setSaldo(cliente.getSaldo() - transacao.getValor());
		}
	}

}
