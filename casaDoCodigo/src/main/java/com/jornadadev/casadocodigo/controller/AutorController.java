package com.jornadadev.casadocodigo.controller;

import com.jornadadev.casadocodigo.controller.dto.NovoAutorDto;
import com.jornadadev.casadocodigo.entity.Autor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/autor")
//3 pontos de carga intrínseca
public class AutorController {

    private final EntityManager em;
    private final ProibeEmailDuplicadoValidator proibeEmailDuplicadoValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(proibeEmailDuplicadoValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrarAutor(@Valid @RequestBody NovoAutorDto novoAutorDto) {
        Autor autor = novoAutorDto.toModel();
        em.persist(autor);
        return ResponseEntity.ok().body(autor.toString());
    }

}

