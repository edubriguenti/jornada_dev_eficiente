package com.jornadadev.mercadolivre.cadastro.adicionapergunta;

public interface Mailer {

    void send(String corpo, String titulo, String from, String to) ;

}
