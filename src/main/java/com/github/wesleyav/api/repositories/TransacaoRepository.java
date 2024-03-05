package com.github.wesleyav.api.repositories;

import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.wesleyav.api.entities.Transacao;

import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

	@Transactional
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Query("SELECT t FROM Transacao t WHERE t.id = :id")
	Transacao findByIdWithLock(@Param("id") Integer id);

	List<Transacao> findByClienteIdOrderByRealizadaEmDesc(Integer id, Limit limit);

}
