package com.github.wesleyav.api.dtos.requests;

import com.github.wesleyav.api.entities.Transacao;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class TransacaoRequestDTO {

	@PositiveOrZero
	private Integer valor;

	@Pattern(regexp = "[cd]", message = "O tipo deve ser 'c' ou 'd'")
	private String tipo;

	@Size(min = 1, max = 10)
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

	public String getTipo() {
		return tipo;
	}

	public String getDescricao() {
		return descricao;
	}

}
