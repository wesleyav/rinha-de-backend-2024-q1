package com.github.wesleyav.api.dtos.responses;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.wesleyav.api.entities.Transacao;

public class TransacaoResponseDTO {

	@JsonProperty(value = "valor")
	private Integer valor;

	@JsonProperty(value = "tipo")
	private String tipo;

	@JsonProperty(value = "descricao")
	private String descricao;

	@JsonProperty(value = "realizada_em")
	private Instant realizadaEm;

	public TransacaoResponseDTO() {
	}

	public TransacaoResponseDTO(Transacao transacao) {
		this.valor = transacao.getValor();
		this.tipo = transacao.getTipo();
		this.descricao = transacao.getDescricao();
		this.realizadaEm = Instant.now();
	}

	public TransacaoResponseDTO(Integer valor, String tipo, String descricao, Instant realizadaEm) {
		this.valor = valor;
		this.tipo = tipo;
		this.descricao = descricao;
		this.realizadaEm = realizadaEm;
	}

	public Integer getValor() {
		return valor;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Instant getRealizadaEm() {
		return realizadaEm;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
