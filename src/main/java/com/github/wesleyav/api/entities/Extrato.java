package com.github.wesleyav.api.entities;

import java.util.ArrayList;
import java.util.List;

public class Extrato {

	private Integer id;

	private Saldo saldo;

	private List<Transacao> transacoes = new ArrayList<Transacao>();

	public Extrato() {
	}

	public Extrato(Saldo saldo) {
		this.saldo = saldo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Saldo getSaldo() {
		return saldo;
	}

	public void setSaldo(Saldo saldo) {
		this.saldo = saldo;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

}
