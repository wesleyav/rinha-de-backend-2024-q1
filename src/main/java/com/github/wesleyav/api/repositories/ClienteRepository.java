package com.github.wesleyav.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.wesleyav.api.entities.Cliente;

import jakarta.persistence.LockModeType;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT c FROM Cliente c WHERE c.id = :id")
	Optional<Cliente> findClienteById(@Param("id") Integer id);
}
