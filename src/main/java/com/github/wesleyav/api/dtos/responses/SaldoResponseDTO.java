package com.github.wesleyav.api.dtos.responses;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SaldoResponseDTO(Integer total, @JsonProperty("data_extrato") Instant dataExtrato, Integer limite) {

}
