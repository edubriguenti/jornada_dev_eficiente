package com.jornadadev.mercadolivre.cadastro.categoria;

import com.jornadadev.mercadolivre.entity.Categoria;
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
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrarCategoria(@Valid @RequestBody CategoriaRequest categoriaRequest) {
        final Categoria categoria = categoriaRequest.toModel(em);
        em.persist(categoria);
        return ResponseEntity.accepted().body(categoria.toString());
    }
}
