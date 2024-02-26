package com.github.wesleyav.api.controllers;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.github.wesleyav.api.entities.Cliente;
import com.github.wesleyav.api.services.ClienteService;
import com.github.wesleyav.api.services.ExtratoService;
import com.github.wesleyav.api.services.TransacaoService;
import com.github.wesleyav.api.services.exceptions.DebitoExcedeLimiteException;
import com.github.wesleyav.api.services.exceptions.DescricaoTransacaoInvalidaException;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	private ClienteService clienteService;
	private TransacaoService transacaoService;
	private ExtratoService extratoService;

	public ClienteController(ClienteService clienteService, TransacaoService transacaoService,
			ExtratoService extratoService) {
		this.clienteService = clienteService;
		this.transacaoService = transacaoService;
		this.extratoService = extratoService;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		Cliente cliente = clienteService.findById(id);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> clientes = clienteService.findAll();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

//	@PostMapping(value = "/{id}/transacoes")
//	public ResponseEntity<ClienteResponseDTO> criarTransacao(@PathVariable Integer id,
//			@RequestBody TransacaoRequestDTO transacaoRequestDTO) {
//
//		ClienteResponseDTO clienteResponseDTO = transacaoService.criarTransacao(id, transacaoRequestDTO);
//		return ResponseEntity.ok(clienteResponseDTO);
//	}

	@PostMapping(value = "/{id}/transacoes")
	public ResponseEntity<ClienteResponseDTO> criarTransacao(@PathVariable Integer id,
			@RequestBody TransacaoRequestDTO transacaoRequestDTO) {
		try {
			ClienteResponseDTO clienteResponseDTO = transacaoService.criarTransacao(id, transacaoRequestDTO);
			return ResponseEntity.ok(clienteResponseDTO);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (DebitoExcedeLimiteException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		} catch (DescricaoTransacaoInvalidaException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@GetMapping(value = "/{id}/extrato", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExtratoResponseDTO> extrato(@PathVariable Integer id) {
		try {
			ExtratoResponseDTO extratoResponseDTO = extratoService.obterExtratoPorClienteId(id);
			return ResponseEntity.ok(extratoResponseDTO);
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Registro não encontrado
		}
	}

}
