package com.github.wesleyav.api.dtos.responses;

import com.github.wesleyav.api.entities.Cliente;

public class ClienteResponseDTO {

	private Integer limite;
	private Integer saldo;

	public ClienteResponseDTO() {
	}

	public ClienteResponseDTO(Cliente cliente) {
		this.limite = cliente.getLimite();
		this.saldo = cliente.getSaldo();
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

}
