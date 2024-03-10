package com.github.wesleyav.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.github.wesleyav.api.services.exceptions.DebitoCausaSaldoNegativoException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "limite", nullable = false)
	private Integer limite;

	@Column(name = "saldo", nullable = false)
	private Integer saldo;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Transacao> transacoes = new ArrayList<>();

	public Cliente() {
	}

	public Cliente(Integer limite, Integer saldo) {
		this.limite = limite;
		this.saldo = saldo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLimite() {
		return limite;
	}

	public void setLimite(Integer limite) {
		this.limite = limite;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	public void realizarCredito(Integer valor) {
		this.saldo += valor;
	}

	public void realizarDebito(Integer valor) {
		if (this.saldo - valor < -this.limite) {
			throw new DebitoCausaSaldoNegativoException();
		}
		this.saldo -= valor;
	}

	public void adicionarTransacao(Transacao transacao) {
		this.transacoes.add(transacao);
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

}