package com.jornadadev.mercadolivre.fechamentocompra;

import com.jornadadev.mercadolivre.config.security.UsuarioLogado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class FechaCompraParte2Controller {

    private final EntityManager em;

    @PostMapping
    @Transactional
    @RequestMapping("compras/{id}/retorno-pagseguro")
    public ResponseEntity<String> processaPagseguro(@NotNull @PathVariable("id") Long idCompra,
                                                         @RequestBody @Valid RetornoPagseguroRequest request) {
        return processaPagamento(idCompra, request);
    }

    @PostMapping
    @Transactional
    @RequestMapping("compras/{id}/retorno-paypal")
    public ResponseEntity<String> processaPaypal(@NotNull @PathVariable("id") Long idCompra,
                                                    @RequestBody @Valid RetornoPaypalRequest request,
                                                    @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        return processaPagamento(idCompra, request);
    }

    private ResponseEntity<String> processaPagamento(@PathVariable("id") @NotNull Long idCompra,
                                                     @RequestBody @Valid RetornoGatewayPagamento request) {
        final Compra compra = em.find(Compra.class, idCompra);
        compra.adicionaTransacao(request);
        em.merge(compra);
        return ResponseEntity.accepted().body(compra.toString());
    }

}
