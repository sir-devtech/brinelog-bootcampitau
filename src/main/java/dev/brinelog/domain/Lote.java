package dev.brinelog.domain;

public record Lote(
        Long id,
        String vegetal,
        double salPercent,
        int dias,
        double temperaturaC,
        Criterio criterio,
        int nota,
        Veredito veredito,
        String motivo) {
}
