package dev.brinelog;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class LoteApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loteSeguroNaJanelaVaiParaOPote() throws Exception {
        mockMvc.perform(post("/lotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "vegetal": "repolho",
                                  "salPercent": 2.5,
                                  "dias": 10,
                                  "temperaturaC": 18,
                                  "criterio": "COMPLETO"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.veredito").value("POTE"))
                .andExpect(jsonPath("$.nota").value(92));

        mockMvc.perform(get("/lotes/prontos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].vegetal").value("repolho"));
    }

    @Test
    void salBaixoComCalorVaiParaDescarte() throws Exception {
        mockMvc.perform(post("/lotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "vegetal": "pepino",
                                  "salPercent": 1,
                                  "dias": 10,
                                  "temperaturaC": 28,
                                  "criterio": "COMPLETO"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.veredito").value("DESCARTE"));
    }

    @Test
    void loteInexistenteResponde404() throws Exception {
        mockMvc.perform(delete("/lotes/999"))
                .andExpect(status().isNotFound());
    }
}
