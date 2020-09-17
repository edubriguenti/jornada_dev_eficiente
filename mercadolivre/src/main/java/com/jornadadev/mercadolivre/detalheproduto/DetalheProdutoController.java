package com.jornadadev.mercadolivre.detalheproduto;

import com.jornadadev.mercadolivre.entity.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class DetalheProdutoController {

    private final EntityManager em;

    @RequestMapping("/{id}")
    @GetMapping
    public DetalheProdutoView obterDetalheProduto(@PathVariable("id") Long idProduto){
        final Produto produto = em.find(Produto.class, idProduto);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        return new DetalheProdutoView(produto);
    }

}
