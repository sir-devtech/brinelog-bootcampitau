package dev.brinelog.domain;

import java.util.List;
import java.util.Optional;

public interface LoteRepositorio {

    Lote salvar(Lote lote);

    List<Lote> listar();

    Optional<Lote> buscar(Long id);

    List<Lote> prontos();

    void apagar(Long id);
}
