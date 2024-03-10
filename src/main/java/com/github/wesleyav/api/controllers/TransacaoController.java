package com.github.wesleyav.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoController {

	// private TransacaoService transacaoService;

	// public TransacaoController(TransacaoService transacaoService) {
	// 	this.transacaoService = transacaoService;
	// }

	// @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<Transacao> findById(@PathVariable Integer id) {
	// 	Transacao transacao = transacaoService.findById(id);
	// 	return new ResponseEntity<>(transacao, HttpStatus.OK);
	// }

	// @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<List<Transacao>> findAll() {
	// 	List<Transacao> transacoes = transacaoService.findAll();
	// 	return new ResponseEntity<>(transacoes, HttpStatus.OK);
	// }

}
