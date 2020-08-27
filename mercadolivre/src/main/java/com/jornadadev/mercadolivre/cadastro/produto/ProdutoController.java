package com.jornadadev.mercadolivre.cadastro.produto;

import com.jornadadev.mercadolivre.config.security.UsuarioLogado;
import com.jornadadev.mercadolivre.entity.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final EntityManager em;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibiCaracteriscaComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrarCategoria(@Valid @RequestBody ProdutoRequest produtoRequest,
                                                     @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        final Produto produto = produtoRequest.toModel(em, usuarioLogado.get());
        em.persist(produto);
        return ResponseEntity.accepted().body(produto.toString());
    }
}
