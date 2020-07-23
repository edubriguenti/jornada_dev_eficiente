package com.jornadadev.casadocodigo.controller;

import com.jornadadev.casadocodigo.entity.Autor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/autor")
public class AutorController {

    private final EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarAutor(@Valid @RequestBody AutorForm autorForm) {
        Autor autor = autorForm.toModel();
        em.persist(autor);
        return ResponseEntity.ok().build();
    }

}
