package dev.brinelog.web;

import dev.brinelog.domain.Criterio;
import dev.brinelog.domain.Lote;
import dev.brinelog.domain.Veredito;

public record LoteResponse(
        Long id,
        String vegetal,
        double salPercent,
        int dias,
        double temperaturaC,
        Criterio criterio,
        int nota,
        Veredito veredito,
        String motivo) {

    public static LoteResponse de(Lote lote) {
        return new LoteResponse(
                lote.id(),
                lote.vegetal(),
                lote.salPercent(),
                lote.dias(),
                lote.temperaturaC(),
                lote.criterio(),
                lote.nota(),
                lote.veredito(),
                lote.motivo());
    }
}
