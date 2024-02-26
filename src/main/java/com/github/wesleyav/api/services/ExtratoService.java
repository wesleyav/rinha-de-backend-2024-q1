package com.github.wesleyav.api.services;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.github.wesleyav.api.dtos.responses.ExtratoResponseDTO;
import com.github.wesleyav.api.dtos.responses.SaldoResponseDTO;
import com.github.wesleyav.api.dtos.responses.TransacaoResponseDTO;
import com.github.wesleyav.api.entities.Cliente;
import com.github.wesleyav.api.entities.Transacao;
import com.github.wesleyav.api.repositories.ClienteRepository;
import com.github.wesleyav.api.repositories.TransacaoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExtratoService {

	private ClienteRepository clienteRepository;
	private TransacaoRepository transacaoRepository;

	public ExtratoService(ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
		this.clienteRepository = clienteRepository;
		this.transacaoRepository = transacaoRepository;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public ExtratoResponseDTO obterExtratoPorClienteId(Integer clienteId) {
		try {
			Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new EntityNotFoundException());

			List<Transacao> ultimasTransacoes = transacaoRepository.findTop10ByClienteOrderByRealizadaEmDesc(cliente);

			List<TransacaoResponseDTO> transacoesResponseDTOs = ultimasTransacoes.stream()
					.map(transacao -> new TransacaoResponseDTO(transacao.getValor(), transacao.getTipo(),
							transacao.getDescricao(), transacao.getRealizadaEm()))
					.collect(Collectors.toList());
			int saldoTotal = cliente.getSaldo();

			SaldoResponseDTO saldoResponseDTO = new SaldoResponseDTO(saldoTotal, cliente.getLimite(), Instant.now());

			return new ExtratoResponseDTO(saldoResponseDTO, transacoesResponseDTOs);
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException();
		}

	}

}
