package com.github.wesleyav.api.dtos.responses;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SaldoResponseDTO(@JsonProperty("total") Integer total, @JsonProperty("data_extrato") Instant dataExtrato,
		@JsonProperty("limite") Integer limite) {
}