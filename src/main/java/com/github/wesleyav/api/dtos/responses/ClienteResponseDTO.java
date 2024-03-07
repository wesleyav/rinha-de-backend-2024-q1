package com.github.wesleyav.api.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClienteResponseDTO(@JsonProperty(value = "limite") Integer limite,
		@JsonProperty(value = "saldo") Integer saldo) {
}