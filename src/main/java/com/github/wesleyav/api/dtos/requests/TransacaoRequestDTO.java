package com.github.wesleyav.api.dtos.requests;

import com.github.wesleyav.api.entities.Transacao;

public class TransacaoRequestDTO {

	private Integer valor;

	private char tipo;

	private String descricao;

	public Transacao convertToDTO(TransacaoRequestDTO dto) {
		Transacao transacao = new Transacao();

		transacao.setValor(dto.getValor());
		transacao.setTipo(dto.getTipo());
		transacao.setDescricao(dto.getDescricao());

		return transacao;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
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

}
