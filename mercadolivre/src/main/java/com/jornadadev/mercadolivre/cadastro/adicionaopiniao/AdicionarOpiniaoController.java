package com.jornadadev.mercadolivre.cadastro.adicionaopiniao;

import com.jornadadev.mercadolivre.config.security.UsuarioLogado;
import com.jornadadev.mercadolivre.entity.Opiniao;
import com.jornadadev.mercadolivre.entity.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/produtos")
@RequiredArgsConstructor
public class AdicionarOpiniaoController {

    private final EntityManager em;

    @PostMapping(value = "/{id}/opiniao")
    @Transactional
    public String adiciona(@RequestBody @Valid NovaOpiniaoRequest request, @PathVariable("id") Long idProduto,
                         @AuthenticationPrincipal UsuarioLogado usuario){
        Produto produto = em.find(Produto.class, idProduto);
        Opiniao opiniao = request.toModel(produto, usuario.get());
        em.persist(opiniao);
        return opiniao.toString();
    }

}
