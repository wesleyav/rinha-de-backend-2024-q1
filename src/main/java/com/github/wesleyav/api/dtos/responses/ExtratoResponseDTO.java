package com.github.wesleyav.api.dtos.responses;

import java.util.List;

public class ExtratoResponseDTO {

	private SaldoResponseDTO saldo;
	private List<TransacaoResponseDTO> ultimas_transacoes;

	public ExtratoResponseDTO(SaldoResponseDTO saldo, List<TransacaoResponseDTO> ultimas_transacoes) {
		this.saldo = saldo;
		this.ultimas_transacoes = ultimas_transacoes;
	}

	public SaldoResponseDTO getSaldo() {
		return saldo;
	}

	public List<TransacaoResponseDTO> getUltimas_transacoes() {
		return ultimas_transacoes;
	}

}
