# Brinelog

Spring Boot API that decides whether a fermentation batch should be jarred, held, or discarded.

## Run

```bash
mvn test
mvn spring-boot:run
```

Open `http://localhost:8080`.

```bash
curl -X POST http://localhost:8080/lotes \
  -H "Content-Type: application/json" \
  -d "{\"vegetal\":\"repolho\",\"salPercent\":2.5,\"dias\":10,\"temperaturaC\":18,\"criterio\":\"COMPLETO\"}"
```

`criterio`: `SEGURANCA`, `ACIDEZ`, `PONTO`, or `COMPLETO`.

| Method | Path |
| --- | --- |
| POST | `/lotes` |
| GET | `/lotes` |
| GET | `/lotes/prontos` |
| GET | `/lotes/{id}` |
| DELETE | `/lotes/{id}` |

H2 console: `http://localhost:8080/h2-console`  
JDBC `jdbc:h2:mem:brinelog`, user `sa`, empty password.

## Architecture

The web layer does not know the rules. The rules do not know the database.

```
web  ->  application  ->  domain
                ^
        infrastructure (JPA + strategies)
```

- **domain** — `Lote`, verdict policy, and the `CriterioLote` contract. No Spring.
- **application** — `LoteAplicacao` is the facade. `AvaliadorDeLote` runs one strategy or all of them.
- **infrastructure** — JPA adapter and the three strategy beans.
- **web** — HTTP, validation, and the page.

Patterns from the challenge:

- **Strategy** — safety, acidity, and jar window are separate classes behind `CriterioLote`.
- **Facade** — controllers only call `LoteAplicacao`.
- **Singleton** — Spring creates each `@Service` and `@Repository` once.

`COMPLETO` averages the three scores. Salt under 2% with heat over 20°C forces `DESCARTE`.

## Em português

Você manda um lote. A API responde **POTE**, **ESPERA** ou **DESCARTE**, com nota e motivo.

A tela em `/` é só a porta de entrada. A regra mora no domínio e o banco fica isolado na infraestrutura.
