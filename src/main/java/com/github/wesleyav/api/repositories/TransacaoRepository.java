package com.github.wesleyav.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.wesleyav.api.entities.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

}
