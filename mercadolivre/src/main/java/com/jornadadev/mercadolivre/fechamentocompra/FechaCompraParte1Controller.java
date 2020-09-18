package com.jornadadev.mercadolivre.fechamentocompra;

import com.jornadadev.mercadolivre.config.security.UsuarioLogado;
import com.jornadadev.mercadolivre.entity.Produto;
import com.jornadadev.mercadolivre.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class FechaCompraParte1Controller {

    private final EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<String> finalixzaCompra(@RequestBody @Valid NovaCompraRequest request,
                                                  @AuthenticationPrincipal UsuarioLogado usuarioLogado,
                                                  UriComponentsBuilder uriComponentsBuilder) throws BindException {

        final Produto produtoASerComprado = em.find(Produto.class, request.getIdProduto());

        final boolean abateu = produtoASerComprado.abateEstoque(request.getQuantidade());
        if (abateu) {
            final Usuario comprador = usuarioLogado.get();
            final Compra novaCompra = new Compra(produtoASerComprado, request.getQuantidade(), comprador, request.getGateway());
            em.persist(novaCompra);
            final String urlRetorno = request.getGateway().criaUrlRetorno(novaCompra, uriComponentsBuilder);
            return ResponseEntity.ok().body(urlRetorno);
        }
        BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
        problemaComEstoque.reject(null,"Não foi possível realizar a compra. Estoque insuficiente");
        throw problemaComEstoque;
    }

}
