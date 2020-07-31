package com.jornadadev.casadocodigo.pais;

import com.jornadadev.casadocodigo.entity.Pais;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pais")
public class PaisController {

    private final EntityManager em;

    @Transactional
    @PostMapping
    public ResponseEntity<String> cadastrarPais(@Valid @RequestBody PaisDto paisDto){
        Pais pais = paisDto.toModel();
        em.persist(pais);
        return ResponseEntity.accepted().body(pais.toString());
    }
}
