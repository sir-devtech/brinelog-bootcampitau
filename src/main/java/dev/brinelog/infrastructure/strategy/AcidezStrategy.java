package dev.brinelog.infrastructure.strategy;

import org.springframework.stereotype.Component;

import dev.brinelog.domain.Avaliacao;
import dev.brinelog.domain.Criterio;
import dev.brinelog.domain.CriterioLote;
import dev.brinelog.domain.LoteDados;

@Component
public class AcidezStrategy implements CriterioLote {

    @Override
    public Criterio tipo() {
        return Criterio.ACIDEZ;
    }

    @Override
    public Avaliacao avaliar(LoteDados dados) {
        int dias = dados.dias();
        if (dias < 3) {
            return new Avaliacao(30, "Menos de 3 dias: acidez ainda não começou.");
        }
        if (dias < 7) {
            return new Avaliacao(55, "Acidez começando.");
        }
        if (dias <= 14) {
            return new Avaliacao(90, "Acidez no ponto.");
        }
        if (dias <= 21) {
            return new Avaliacao(70, "Acidez passando do ideal.");
        }
        return new Avaliacao(40, "Mais de 21 dias: passou do ponto.");
    }
}
