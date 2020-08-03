package com.jornadadev.casadocodigo.fechamentocompra;

import com.jornadadev.casadocodigo.entity.Compra;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/compra")
public class FechaCompraController {

    private final EntityManager em;
    private final VerificarDocumentoCpfCnpjValidator verificarDocumentoCpfCnpjValidator;
    private final EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;


    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(verificarDocumentoCpfCnpjValidator, estadoPertenceAPaisValidator);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> fechaCompra(@Valid @RequestBody NovaCompraRequest novaCompraRequest) {
        final Compra compra = novaCompraRequest.toModel(em);
        //em.persist(compra);
        return ResponseEntity.accepted().body(compra.toString());
    }
}

