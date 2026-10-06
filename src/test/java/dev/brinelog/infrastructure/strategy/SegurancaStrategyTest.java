package dev.brinelog.infrastructure.strategy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dev.brinelog.domain.LoteDados;

class SegurancaStrategyTest {

    @Test
    void salBaixoComCalorFicaCritico() {
        var nota = new SegurancaStrategy()
                .avaliar(new LoteDados("pepino", 1, 10, 28))
                .nota();
        assertTrue(nota < 40);
    }
}
