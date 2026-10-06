package dev.brinelog.application;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import dev.brinelog.domain.Avaliacao;
import dev.brinelog.domain.Criterio;
import dev.brinelog.domain.CriterioLote;
import dev.brinelog.domain.LoteDados;
import dev.brinelog.domain.PoliticaDeVeredito;
import dev.brinelog.domain.Resultado;

public class AvaliadorDeLote {

    private final Map<Criterio, CriterioLote> estrategias;
    private final PoliticaDeVeredito politica = new PoliticaDeVeredito();

    public AvaliadorDeLote(List<CriterioLote> estrategias) {
        this.estrategias = estrategias.stream()
                .collect(Collectors.toMap(CriterioLote::tipo, Function.identity()));
    }

    public Resultado avaliar(Criterio criterio, LoteDados dados) {
        Avaliacao avaliacao = nota(criterio, dados);
        int seguranca = estrategias.get(Criterio.SEGURANCA).avaliar(dados).nota();
        return new Resultado(
                avaliacao.nota(),
                politica.decidir(avaliacao.nota(), politica.segurancaCritica(criterio, seguranca)),
                avaliacao.motivo());
    }

    private Avaliacao nota(Criterio criterio, LoteDados dados) {
        if (criterio != Criterio.COMPLETO) {
            return estrategias.get(criterio).avaliar(dados);
        }
        List<Avaliacao> partes = List.of(Criterio.SEGURANCA, Criterio.ACIDEZ, Criterio.PONTO).stream()
                .map(tipo -> estrategias.get(tipo).avaliar(dados))
                .toList();
        int media = (int) Math.round(partes.stream().mapToInt(Avaliacao::nota).average().orElse(0));
        String motivo = partes.stream().map(Avaliacao::motivo).collect(Collectors.joining(" "));
        return new Avaliacao(media, motivo);
    }
}
