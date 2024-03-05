package com.github.wesleyav.api.dtos.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExtratoResponseDTO(

		@JsonProperty("saldo") SaldoResponseDTO saldo,
		@JsonProperty("ultimas_transacoes") List<TransacaoResponseDTO> ultimasTransacoes) {

}
