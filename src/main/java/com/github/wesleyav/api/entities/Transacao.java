package com.github.wesleyav.api.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacao")
public class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer valor;

	private char tipo;

	private String descricao;

	@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", timezone = "UTC")
	private Instant realizadaEm;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@JsonIgnore
	private Cliente cliente;

	public Transacao() {
	}

	public Transacao(Integer valor, char tipo, String descricao, Instant realizadaEm) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor deve ser um número inteiro positivo.");
		}
		if (tipo != 'c' && tipo != 'd') {
			throw new IllegalArgumentException("O tipo deve ser 'c' para crédito ou 'd' para débito.");
		}
		if (descricao == null || descricao.length() < 1 || descricao.length() > 10) {
			throw new IllegalArgumentException("A descrição deve ter entre 1 e 10 caracteres.");
		}
		this.valor = valor;
		this.tipo = tipo;
		this.descricao = descricao;
		this.realizadaEm = realizadaEm;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Instant getRealizadaEm() {
		return realizadaEm;
	}

	public void setRealizadaEm(Instant realizadaEm) {
		this.realizadaEm = realizadaEm;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		return Objects.equals(id, other.id);
	}
}
