package dev.brinelog.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import dev.brinelog.domain.Criterio;
import dev.brinelog.domain.Lote;
import dev.brinelog.domain.Veredito;

@Entity
@Table(name = "lote")
class LoteJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vegetal;
    private double salPercent;
    private int dias;
    private double temperaturaC;

    @Enumerated(EnumType.STRING)
    private Criterio criterio;

    private int nota;

    @Enumerated(EnumType.STRING)
    private Veredito veredito;

    private String motivo;

    static LoteJpa de(Lote lote) {
        LoteJpa entidade = new LoteJpa();
        entidade.vegetal = lote.vegetal();
        entidade.salPercent = lote.salPercent();
        entidade.dias = lote.dias();
        entidade.temperaturaC = lote.temperaturaC();
        entidade.criterio = lote.criterio();
        entidade.nota = lote.nota();
        entidade.veredito = lote.veredito();
        entidade.motivo = lote.motivo();
        return entidade;
    }

    Lote paraDominio() {
        return new Lote(id, vegetal, salPercent, dias, temperaturaC, criterio, nota, veredito, motivo);
    }
}
