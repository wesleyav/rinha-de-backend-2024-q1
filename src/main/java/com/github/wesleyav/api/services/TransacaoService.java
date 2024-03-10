package com.github.wesleyav.api.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wesleyav.api.dtos.requests.TransacaoRequestDTO;
import com.github.wesleyav.api.dtos.responses.ClienteResponseDTO;
import com.github.wesleyav.api.entities.Cliente;
import com.github.wesleyav.api.entities.Transacao;
import com.github.wesleyav.api.repositories.ClienteRepository;
import com.github.wesleyav.api.services.exceptions.ClienteNotFoundException;
import com.github.wesleyav.api.services.exceptions.DebitoExcedeLimiteException;
import com.github.wesleyav.api.services.exceptions.UnprocessableEntityException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class TransacaoService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public ClienteResponseDTO transacao(Integer idCliente, TransacaoRequestDTO transacaoRequestDTO) {

		validarPayload(transacaoRequestDTO);

		// Cliente cliente = entityManager.find(Cliente.class, idCliente,
		// LockModeType.PESSIMISTIC_WRITE);
		Cliente cliente = clienteRepository.findClienteById(idCliente)
				.orElseThrow(() -> new ClienteNotFoundException("Cliente nao encontrado"));

		validarValor(cliente, transacaoRequestDTO);

		Transacao transacao = criarTransacao(transacaoRequestDTO, cliente);

		atualizarSaldo(cliente, transacaoRequestDTO);

		cliente.adicionarTransacao(transacao);
		clienteRepository.save(cliente);

		ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente.getLimite(), cliente.getSaldo());
		return clienteResponseDTO;
	}

	public void atualizarSaldo(Cliente cliente, TransacaoRequestDTO transacaoRequestDTO) {
		if ("c".equals(transacaoRequestDTO.tipo())) {
			cliente.realizarCredito(transacaoRequestDTO.valor().intValue());
		} else if ("d".equals(transacaoRequestDTO.tipo())) {
			cliente.realizarDebito(transacaoRequestDTO.valor().intValue());
		}
	}

	public void validarValor(Cliente cliente, TransacaoRequestDTO transacaoRequestDTO) {
		if (transacaoRequestDTO.valor() > cliente.getLimite()) {
			throw new DebitoExcedeLimiteException();
		}
	}

	public void validarPayload(TransacaoRequestDTO dto) {
		if (dto.descricao() == null || dto.valor() == null || dto.tipo() == null) {
			throw new UnprocessableEntityException();
		}

		if (dto.descricao().length() < 1 || dto.descricao().length() > 10) {
			throw new UnprocessableEntityException();
		}

		double valor = dto.valor();
		int intValue = (int) valor;

		if (valor != intValue) {
			throw new UnprocessableEntityException();
		}

		if (!dto.tipo().equals("c") && !dto.tipo().equals("d")) {
			throw new UnprocessableEntityException();
		}
	}

	public Transacao criarTransacao(TransacaoRequestDTO transacaoRequestDTO, Cliente cliente) {
		Transacao transacao = new Transacao();
		transacao.setValor(transacaoRequestDTO.valor().intValue());
		transacao.setTipo(transacaoRequestDTO.tipo());
		transacao.setDescricao(transacaoRequestDTO.descricao());
		transacao.setRealizadaEm(Instant.now());
		transacao.setCliente(cliente);
		return transacao;
	}

}
