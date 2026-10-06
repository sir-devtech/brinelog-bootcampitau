package dev.brinelog.application;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.brinelog.domain.CriterioLote;
import dev.brinelog.domain.Lote;
import dev.brinelog.domain.LoteDados;
import dev.brinelog.domain.LoteRepositorio;
import dev.brinelog.domain.Resultado;

@Service
public class LoteAplicacao {

    private final LoteRepositorio lotes;
    private final AvaliadorDeLote avaliador;

    public LoteAplicacao(LoteRepositorio lotes, List<CriterioLote> estrategias) {
        this.lotes = lotes;
        this.avaliador = new AvaliadorDeLote(estrategias);
    }

    public Lote registrar(LoteDados dados, dev.brinelog.domain.Criterio criterio) {
        Resultado resultado = avaliador.avaliar(criterio, dados);
        return lotes.salvar(new Lote(
                null,
                dados.vegetal(),
                dados.salPercent(),
                dados.dias(),
                dados.temperaturaC(),
                criterio,
                resultado.nota(),
                resultado.veredito(),
                resultado.motivo()));
    }

    public List<Lote> listar() {
        return lotes.listar();
    }

    public Lote buscar(Long id) {
        return lotes.buscar(id).orElseThrow(() -> new LoteNaoEncontradoException(id));
    }

    public List<Lote> prontos() {
        return lotes.prontos();
    }

    public void apagar(Long id) {
        if (lotes.buscar(id).isEmpty()) {
            throw new LoteNaoEncontradoException(id);
        }
        lotes.apagar(id);
    }
}
