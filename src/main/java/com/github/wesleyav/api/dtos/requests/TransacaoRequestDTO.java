package com.github.wesleyav.api.dtos.requests;

import jakarta.validation.constraints.NotNull;

public class TransacaoRequestDTO {

	@NotNull
	private Integer valor;

	@NotNull
	private String tipo;

	@NotNull
	private String descricao;

	public TransacaoRequestDTO() {
	}

	public TransacaoRequestDTO(Integer valor, String tipo, String descricao) {
		this.valor = valor;
		this.tipo = tipo;
		this.descricao = descricao;
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

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
