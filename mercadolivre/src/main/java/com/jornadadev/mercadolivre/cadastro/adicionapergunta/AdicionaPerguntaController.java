package com.jornadadev.mercadolivre.cadastro.adicionapergunta;

import com.jornadadev.mercadolivre.config.security.UsuarioLogado;
import com.jornadadev.mercadolivre.entity.Pergunta;
import com.jornadadev.mercadolivre.entity.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class AdicionaPerguntaController {

    private final EntityManager em;
    private final Emails emails;

    @PostMapping
    @RequestMapping("/{id}/perguntas")
    @Transactional
    public String adiciona(@RequestBody @Valid PerguntaRequest request,
                           @NotNull @PathVariable("id") Long idProduto,
                           @AuthenticationPrincipal UsuarioLogado interessado) {
        final Produto produto = em.find(Produto.class, idProduto);
        Pergunta novaPergunta = request.toModel(interessado.get(), produto);
        em.persist(novaPergunta);
        emails.novaPergunta(novaPergunta);
        return produto.getPerguntas().toString();

    }
}
