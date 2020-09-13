package com.jornadadev.mercadolivre.cadastro.adicionapergunta;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class RealMailer implements Mailer {
    public void send(String corpo, String titulo, String from, String to) {
        System.out.println("Enviando email REAIS");
        System.out.println(corpo);
        System.out.println(titulo);
        System.out.println(from);
        System.out.println(to);
    }
}
