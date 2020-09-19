package com.jornadadev.mercadolivre.fechamentocompra;

import org.springframework.stereotype.Service;

@Service
public class EmailService implements EventoCompraSucesso {
    public void processa(Compra compra) {
        System.out.println("enviando e-mail para "+ compra.getComprador().getEmail());
    }
}
