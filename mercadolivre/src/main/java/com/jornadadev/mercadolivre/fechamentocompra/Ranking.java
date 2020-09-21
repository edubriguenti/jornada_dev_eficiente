package com.jornadadev.mercadolivre.fechamentocompra;

import org.springframework.stereotype.Service;

@Service
public class Ranking implements EventoCompraSucesso {
    public void processa(Compra compra) {
        System.out.println("Alterando o ranking para o dono do produto: "+compra.getDonoDoProduto());
    }
}
