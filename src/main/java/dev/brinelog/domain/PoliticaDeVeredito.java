package dev.brinelog.domain;

public final class PoliticaDeVeredito {

    public Veredito decidir(int nota, boolean segurancaCritica) {
        if (segurancaCritica || nota < 50) {
            return Veredito.DESCARTE;
        }
        if (nota >= 75) {
            return Veredito.POTE;
        }
        return Veredito.ESPERA;
    }

    public boolean segurancaCritica(Criterio criterio, int notaSeguranca) {
        return criterio == Criterio.COMPLETO && notaSeguranca < 40;
    }
}
