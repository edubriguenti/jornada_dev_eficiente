package com.jornadadev.casadocodigo.categoria;

import com.jornadadev.casadocodigo.entity.Categoria;
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
@RequestMapping("/categoria")
//3 pontos de carga intrínseca
public class CategoriaController {

    private final EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrarAutor(@Valid @RequestBody CategoriaDto categoriaDto) {
        final Categoria categoria = categoriaDto.toModel();
        em.persist(categoria);
        return ResponseEntity.ok().body(categoria.toString());
    }
}

