package dev.brinelog.infrastructure.strategy;

import org.springframework.stereotype.Component;

import dev.brinelog.domain.Avaliacao;
import dev.brinelog.domain.Criterio;
import dev.brinelog.domain.CriterioLote;
import dev.brinelog.domain.LoteDados;

@Component
public class PontoStrategy implements CriterioLote {

    @Override
    public Criterio tipo() {
        return Criterio.PONTO;
    }

    @Override
    public Avaliacao avaliar(LoteDados dados) {
        boolean janela = dados.dias() >= 7 && dados.dias() <= 14;
        if (janela && dados.salPercent() >= 2) {
            return new Avaliacao(95, "Janela certa e sal suficiente: pronto para o pote.");
        }
        if (dados.dias() < 7) {
            return new Avaliacao(45, "Ainda cedo para envasar.");
        }
        return new Avaliacao(50, "Fora da janela ideal de 7 a 14 dias.");
    }
}
