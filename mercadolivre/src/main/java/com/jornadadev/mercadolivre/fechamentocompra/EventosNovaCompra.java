package com.jornadadev.mercadolivre.fechamentocompra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventosNovaCompra {

    private final Set<EventoCompraSucesso> eventosSucesso;

    public void processa(Compra compra){
        if (compra.processadaComSucesso()) {
            eventosSucesso.forEach(eventoCompraSucesso
                    -> eventoCompraSucesso.processa(compra));
        } else {
            //eventos falha
        }
    }

}
