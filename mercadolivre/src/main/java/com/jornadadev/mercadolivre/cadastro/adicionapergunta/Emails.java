package com.jornadadev.mercadolivre.cadastro.adicionapergunta;

import com.jornadadev.mercadolivre.entity.Pergunta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class Emails {

    private final Mailer mailer;

    public void novaPergunta(@NotNull @Valid Pergunta pergunta){
        //retTemplate

        mailer.send("<html>...</html>",
                pergunta.getTitulo(),
                pergunta.getInteressado().getEmail(),
                pergunta.getDonoDoProduto().getEmail());
    }

}
