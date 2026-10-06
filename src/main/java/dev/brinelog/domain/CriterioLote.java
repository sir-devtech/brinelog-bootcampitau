package dev.brinelog.domain;

public interface CriterioLote {

    Criterio tipo();

    Avaliacao avaliar(LoteDados dados);
}
