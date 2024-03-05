package com.github.wesleyav.api.controllers;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private TransacaoService transacaoService;

	@Autowired
	private ExtratoService extratoService;

	@PostMapping(value = "/{id}/transacoes")
	public ResponseEntity<ClienteResponseDTO> transacao(@PathVariable Integer id,
			@Valid @RequestBody TransacaoRequestDTO transacaoRequestDTO) {
		ClienteResponseDTO clienteResponseDTO = transacaoService.transacao(id, transacaoRequestDTO);
		return new ResponseEntity<ClienteResponseDTO>(clienteResponseDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}/extrato")
	public ResponseEntity<ExtratoResponseDTO> extrato(@PathVariable("id") Integer id) {

		ExtratoResponseDTO extratoResponseDTO = extratoService.extrato(id);

		return new ResponseEntity<>(extratoResponseDTO, HttpStatus.OK);
	}

}