package com.github.wesleyav.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.wesleyav.api.dtos.requests.TransacaoRequestDTO;
import com.github.wesleyav.api.dtos.responses.ClienteResponseDTO;
import com.github.wesleyav.api.dtos.responses.ExtratoResponseDTO;
import com.github.wesleyav.api.services.ExtratoService;
import com.github.wesleyav.api.services.TransacaoService;
import com.github.wesleyav.api.services.exceptions.ClienteNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private TransacaoService transacaoService;

	@Autowired
	private ExtratoService extratoService;

	@PostMapping(value = "/{id}/transacoes")
	public ResponseEntity<ClienteResponseDTO> transacao(@PathVariable Integer id,
			@RequestBody @Valid TransacaoRequestDTO transacaoRequestDTO) {
		logger.info("Requisicao POST recebida em /clientes/" + id + "/transacoes");

		if (id < 1 || id > 5) {
			throw new ClienteNotFoundException("Cliente nao encontrado");
		}

		ClienteResponseDTO clienteResponseDTO = transacaoService.transacao(id, transacaoRequestDTO);
		return new ResponseEntity<ClienteResponseDTO>(clienteResponseDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}/extrato")
	public ResponseEntity<ExtratoResponseDTO> extrato(@PathVariable("id") @Valid Integer id) {
		logger.info("Requisicao GET recebida em /clientes/" + id + "/extrato");
		ExtratoResponseDTO extratoResponseDTO = extratoService.extrato(id);

		return new ResponseEntity<>(extratoResponseDTO, HttpStatus.OK);
	}

}