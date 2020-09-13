package com.jornadadev.mercadolivre.cadastro.adicionapergunta;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class FakeMailer implements Mailer {
    public void send(String corpo, String titulo, String from, String to) {
        System.out.println("Enviando email FAKE");
        System.out.println(corpo);
        System.out.println(titulo);
        System.out.println(from);
        System.out.println(to);
    }
}
