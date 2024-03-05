package com.github.wesleyav.api.entities;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "transacao")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cliente_id")
	public Integer clienteId;

	@Column(name = "valor")
	private Integer valor;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "descricao")
	private String descricao;

	@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", timezone = "UTC")
	private Instant realizadaEm;

	@Version
	private Integer version;

	public Transacao() {
	}

	public Transacao(Integer id, Integer clienteId, Integer valor, String tipo, String descricao, Instant realizadaEm,
			Integer version) {
		this.id = id;
		this.clienteId = clienteId;
		this.valor = valor;
		this.tipo = tipo;
		this.descricao = descricao;
		this.realizadaEm = realizadaEm;
		this.version = version;
	}

	public Transacao(Integer valor, String tipo, String descricao, Instant realizadaEm, Cliente cliente) {
		this.valor = valor;
		this.tipo = tipo;
		this.descricao = descricao;
		this.realizadaEm = realizadaEm;
		this.clienteId = cliente.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Instant getRealizadaEm() {
		return realizadaEm;
	}

	public void setRealizadaEm(Instant realizadaEm) {
		this.realizadaEm = realizadaEm;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}