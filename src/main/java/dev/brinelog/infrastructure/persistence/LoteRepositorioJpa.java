package dev.brinelog.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.brinelog.domain.Lote;
import dev.brinelog.domain.LoteRepositorio;
import dev.brinelog.domain.Veredito;

@Repository
public class LoteRepositorioJpa implements LoteRepositorio {

    private final LoteJpaRepository lotes;

    public LoteRepositorioJpa(LoteJpaRepository lotes) {
        this.lotes = lotes;
    }

    @Override
    public Lote salvar(Lote lote) {
        return lotes.save(LoteJpa.de(lote)).paraDominio();
    }

    @Override
    public List<Lote> listar() {
        return lotes.findAll().stream().map(LoteJpa::paraDominio).toList();
    }

    @Override
    public Optional<Lote> buscar(Long id) {
        return lotes.findById(id).map(LoteJpa::paraDominio);
    }

    @Override
    public List<Lote> prontos() {
        return lotes.findByVereditoOrderByNotaDesc(Veredito.POTE).stream()
                .map(LoteJpa::paraDominio)
                .toList();
    }

    @Override
    public void apagar(Long id) {
        lotes.deleteById(id);
    }
}
