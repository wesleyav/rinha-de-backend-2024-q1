package com.github.wesleyav.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record TransacaoRequestDTO(@NotNull @PositiveOrZero Integer valor,
		@NotEmpty @Pattern(regexp = "[cd]") String tipo, @NotBlank @Size(max = 10) String descricao) {
}