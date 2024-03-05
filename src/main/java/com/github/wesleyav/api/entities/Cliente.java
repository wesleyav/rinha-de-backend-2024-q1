package com.github.wesleyav.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "limite", nullable = false)
	private Integer limite;

	@Column(name = "saldo", nullable = false)
	private Integer saldo;

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
		this.saldo -= valor;
	}

}