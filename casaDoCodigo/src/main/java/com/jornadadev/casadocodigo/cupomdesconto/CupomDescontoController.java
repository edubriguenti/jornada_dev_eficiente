package com.jornadadev.casadocodigo.cupomdesconto;

import com.jornadadev.casadocodigo.entity.CupomDesconto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/cupons")
@RequiredArgsConstructor
public class CupomDescontoController {

    private final EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastraCupom(@Valid @RequestBody NovoCupomDescontoRequest request) {
        CupomDesconto cupomDesconto = request.toModel();
        em.persist(cupomDesconto);
        return ResponseEntity.accepted().body(cupomDesconto.toString());
    }
}
