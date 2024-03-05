package com.github.wesleyav.api.services;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import com.github.wesleyav.api.dtos.responses.ExtratoResponseDTO;
import com.github.wesleyav.api.dtos.responses.SaldoResponseDTO;
import com.github.wesleyav.api.dtos.responses.TransacaoResponseDTO;
import com.github.wesleyav.api.entities.Cliente;
import com.github.wesleyav.api.repositories.ClienteRepository;
import com.github.wesleyav.api.repositories.TransacaoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ExtratoService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Transactional
	public ExtratoResponseDTO extrato(Integer id) {

		if (id < 1 || id > 5) {
			throw new EntityNotFoundException();
		}

		try {
			Cliente cliente = clienteRepository.findById(id).orElseThrow(EntityNotFoundException::new);

			SaldoResponseDTO saldoResponseDTO = new SaldoResponseDTO(cliente.getSaldo(), Instant.now(),
					cliente.getLimite());

			List<TransacaoResponseDTO> transacoes = transacaoRepository
					.findByClienteIdOrderByRealizadaEmDesc(id, Limit.of(10)).stream()
					.map(t -> new TransacaoResponseDTO(t.getValor(), t.getTipo(), t.getDescricao(), t.getRealizadaEm()))
					.toList();

			ExtratoResponseDTO extrato = new ExtratoResponseDTO(saldoResponseDTO, transacoes);
			return extrato;
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException();
		}
	}

}
