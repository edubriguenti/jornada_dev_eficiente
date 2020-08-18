package com.jornadadev.casadocodigo.detalhescompra;

import com.jornadadev.casadocodigo.entity.Compra;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/compra")
@RequiredArgsConstructor
public class DetalhesCompraController {

    public final EntityManager em;

    @GetMapping("/{id}")
    public ResponseEntity<DetalheCompraResponse> detalheCompra(@Valid @NotNull @Positive
                                                                   @PathVariable("id") Integer idCompra) {
        final Compra compra = em.find(Compra.class, idCompra);
        if (compra == null) {
            return ResponseEntity.notFound().build();
        }
        final DetalheCompraResponse compraResponse = new DetalheCompraResponse(compra);
        return ResponseEntity.ok().body(compraResponse);
    }
}
