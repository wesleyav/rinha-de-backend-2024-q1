package com.github.wesleyav.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.wesleyav.api.entities.Cliente;
import com.github.wesleyav.api.repositories.ClienteRepository;
import com.github.wesleyav.api.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Transactional
	public Cliente findById(Integer clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException(clienteId));
	}

	@Transactional
	public List<Cliente> findAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}

}
