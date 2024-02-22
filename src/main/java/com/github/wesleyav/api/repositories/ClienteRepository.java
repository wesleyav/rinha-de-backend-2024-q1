package com.github.wesleyav.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.wesleyav.api.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
