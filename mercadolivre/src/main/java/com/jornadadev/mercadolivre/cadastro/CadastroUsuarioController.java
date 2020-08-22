package com.jornadadev.mercadolivre.cadastro;

import com.jornadadev.mercadolivre.entity.Usuario;
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
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class CadastroUsuarioController {

    private final EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@Valid @RequestBody CadastroUsuarioRequest cadastroUsuarioRequest) {
        Usuario usuario = cadastroUsuarioRequest.toModel();
        em.persist(usuario);
        System.out.println(usuario);
        return ResponseEntity.accepted().build();
    }


}
