package dev.brinelog.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import dev.brinelog.domain.Criterio;

public record LoteRequest(
        @NotBlank String vegetal,
        @PositiveOrZero double salPercent,
        @PositiveOrZero int dias,
        double temperaturaC,
        @NotNull Criterio criterio) {
}
