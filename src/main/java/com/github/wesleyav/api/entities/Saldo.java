package com.github.wesleyav.api.entities;

import java.time.Instant;

public class Saldo {

	private Integer id;

	private Integer total;

	private Instant dataExtrato;

	private Integer limite;

	public Saldo() {
	}

	public Saldo(Integer total, Instant dataExtrato, Integer limite) {
		this.total = total;
		this.dataExtrato = dataExtrato;
		this.limite = limite;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Instant getDataExtrato() {
		return dataExtrato;
	}

	public void setDataExtrato(Instant dataExtrato) {
		this.dataExtrato = dataExtrato;
	}

	public Integer getLimite() {
		return limite;
	}

	public void setLimite(Integer limite) {
		this.limite = limite;
	}

}
