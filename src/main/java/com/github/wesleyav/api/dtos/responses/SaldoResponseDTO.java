package com.github.wesleyav.api.dtos.responses;

import java.time.Instant;

public class SaldoResponseDTO {

	private Integer total;
	private Instant data_extrato;
	private Integer limite;

	public SaldoResponseDTO(Integer saldoTotal, Integer limite, Instant data_extrato) {
		this.total = saldoTotal;
		this.data_extrato = data_extrato;
		this.limite = limite;
	}

	public Integer getTotal() {
		return total;
	}

	public Instant getData_extrato() {
		return data_extrato;
	}

	public Integer getLimite() {
		return limite;
	}

}
