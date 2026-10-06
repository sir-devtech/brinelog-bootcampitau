package dev.brinelog.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import dev.brinelog.application.LoteAplicacao;
import dev.brinelog.domain.LoteDados;

@RestController
@RequestMapping("/lotes")
public class LoteController {

    private final LoteAplicacao aplicacao;

    public LoteController(LoteAplicacao aplicacao) {
        this.aplicacao = aplicacao;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoteResponse criar(@Valid @RequestBody LoteRequest pedido) {
        LoteDados dados = new LoteDados(
                pedido.vegetal().trim(),
                pedido.salPercent(),
                pedido.dias(),
                pedido.temperaturaC());
        return LoteResponse.de(aplicacao.registrar(dados, pedido.criterio()));
    }

    @GetMapping
    public List<LoteResponse> listar() {
        return aplicacao.listar().stream().map(LoteResponse::de).toList();
    }

    @GetMapping("/prontos")
    public List<LoteResponse> prontos() {
        return aplicacao.prontos().stream().map(LoteResponse::de).toList();
    }

    @GetMapping("/{id}")
    public LoteResponse buscar(@PathVariable Long id) {
        return LoteResponse.de(aplicacao.buscar(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable Long id) {
        aplicacao.apagar(id);
    }
}
