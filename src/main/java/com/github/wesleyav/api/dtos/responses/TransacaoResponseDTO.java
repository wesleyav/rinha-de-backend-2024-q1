package com.github.wesleyav.api.dtos.responses;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransacaoResponseDTO(@JsonProperty(value = "valor") Integer valor,
		@JsonProperty(value = "tipo") String tipo, @JsonProperty(value = "descricao") String descricao,
		@JsonProperty(value = "realizada_em") Instant realizadaEm) {
}