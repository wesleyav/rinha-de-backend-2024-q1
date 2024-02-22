package com.github.wesleyav.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.wesleyav.api.entities.Cliente;
import com.github.wesleyav.api.entities.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

	List<Transacao> findTop10ByClienteOrderByRealizadaEmDesc(Cliente cliente);

}
