package com.jornadadev.mercadolivre.cadastro.produto;

import com.jornadadev.mercadolivre.config.security.UsuarioLogado;
import com.jornadadev.mercadolivre.entity.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Slf4j
public class ProdutoController {

    private final EntityManager em;
    private final UploaderFake uploaderFake;

    @InitBinder(value = "ProdutoRequest")
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibiCaracteriscaComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrarProduto(@Valid @RequestBody ProdutoRequest produtoRequest,
                                                   @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        final Produto produto = produtoRequest.toModel(em, usuarioLogado.get());
        em.persist(produto);
        return ResponseEntity.accepted().body(produto.toString());
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public String adicionaImagens(@PathVariable("id") Long id, @Valid NovasImagensRequest request,
                                  @AuthenticationPrincipal UsuarioLogado usuario) {
        log.info(request.toString());
        /*
        1) Enviar as imagens para um bucket
        2) Pegar o link de todas as imagens
        3) associar o link com o produto em questão
        4) preciso carregar o produto
        5) depois que associar preciso atualizar a nova versão do produto
         */

        Set<String> links = uploaderFake.upload(request.getImagens());
        log.info(links.toString());
        final Produto produto = em.find(Produto.class, id);
        if (!produto.pertenceAoUsuario(usuario.get())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        produto.associaImagens(links);
        em.merge(produto);
        return produto.toString();
    }
}
