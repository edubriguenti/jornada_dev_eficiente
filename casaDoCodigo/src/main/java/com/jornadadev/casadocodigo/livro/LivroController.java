package com.jornadadev.casadocodigo.livro;

import com.jornadadev.casadocodigo.entity.Livro;
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

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/livro")
//3 pontos de carga intrínseca
public class LivroController {

    private final EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrarLivro(@Valid @RequestBody LivroDto livroDto) {
        System.out.println(livroDto);
        final Livro livro = livroDto.toModel(em);
        em.persist(livro);
        return ResponseEntity.ok().body(livro.toString());
    }
}

