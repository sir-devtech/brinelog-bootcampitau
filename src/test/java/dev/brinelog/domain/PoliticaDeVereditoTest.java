package dev.brinelog.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PoliticaDeVereditoTest {

    private final PoliticaDeVeredito politica = new PoliticaDeVeredito();

    @Test
    void notaAltaVaiParaOPote() {
        assertEquals(Veredito.POTE, politica.decidir(92, false));
    }

    @Test
    void notaMediaEspera() {
        assertEquals(Veredito.ESPERA, politica.decidir(60, false));
    }

    @Test
    void segurancaCriticaDescartaMesmoComNotaAlta() {
        assertEquals(Veredito.DESCARTE, politica.decidir(90, true));
    }
}
