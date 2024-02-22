package com.github.wesleyav.api.dtos.responses;

import java.time.Instant;

public class TransacaoResponseDTO {

	private Integer valor;
	private char tipo;
	private String descricao;
	private Instant realizada_em;

	public TransacaoResponseDTO(Integer valor, char tipo, String descricao, Instant realizada_em) {
		this.valor = valor;
		this.tipo = tipo;
		this.descricao = descricao;
		this.realizada_em = realizada_em;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Instant getRealizada_em() {
		return realizada_em;
	}

	public void setRealizada_em(Instant realizada_em) {
		this.realizada_em = realizada_em;
	}

}
