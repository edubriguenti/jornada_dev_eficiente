package com.jornadadev.mercadolivre.fechamentocompra;

import org.springframework.stereotype.Service;

@Service
public class NotaFiscal implements EventoCompraSucesso {
    public void processa(Compra compra) {
        System.out.println("Processando notafiscal para compra "+ compra);
    }
}
