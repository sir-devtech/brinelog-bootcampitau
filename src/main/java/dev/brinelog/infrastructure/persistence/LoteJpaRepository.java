package dev.brinelog.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.brinelog.domain.Veredito;

interface LoteJpaRepository extends JpaRepository<LoteJpa, Long> {

    List<LoteJpa> findByVereditoOrderByNotaDesc(Veredito veredito);
}
