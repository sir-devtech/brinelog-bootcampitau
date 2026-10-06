package dev.brinelog.application;

public class LoteNaoEncontradoException extends RuntimeException {

    public LoteNaoEncontradoException(Long id) {
        super("Lote " + id + " não encontrado.");
    }
}
