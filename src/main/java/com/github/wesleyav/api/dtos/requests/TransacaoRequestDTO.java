package com.github.wesleyav.api.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record TransacaoRequestDTO(@NotNull @PositiveOrZero Double valor, String tipo, String descricao) {
}