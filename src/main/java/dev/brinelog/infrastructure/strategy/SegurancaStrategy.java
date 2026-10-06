package dev.brinelog.infrastructure.strategy;

import org.springframework.stereotype.Component;

import dev.brinelog.domain.Avaliacao;
import dev.brinelog.domain.Criterio;
import dev.brinelog.domain.CriterioLote;
import dev.brinelog.domain.LoteDados;

@Component
public class SegurancaStrategy implements CriterioLote {

    @Override
    public Criterio tipo() {
        return Criterio.SEGURANCA;
    }

    @Override
    public Avaliacao avaliar(LoteDados dados) {
        if (dados.salPercent() < 2 && dados.temperaturaC() > 20) {
            return new Avaliacao(10, "Sal baixo com calor: risco de o lote estragar.");
        }
        if (dados.salPercent() < 2) {
            return new Avaliacao(35, "Sal abaixo de 2%: faixa insegura.");
        }
        if (dados.temperaturaC() > 25) {
            return new Avaliacao(40, "Temperatura acima de 25°C.");
        }
        if (dados.salPercent() <= 5 && dados.temperaturaC() <= 22) {
            return new Avaliacao(90, "Sal e temperatura na faixa segura.");
        }
        return new Avaliacao(60, "Segurança aceitável, mas fora da faixa ideal.");
    }
}
