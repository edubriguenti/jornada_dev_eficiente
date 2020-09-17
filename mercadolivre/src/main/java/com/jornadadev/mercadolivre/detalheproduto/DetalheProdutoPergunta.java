package com.jornadadev.mercadolivre.detalheproduto;

import com.jornadadev.mercadolivre.entity.Pergunta;
import lombok.Getter;

@Getter
public class DetalheProdutoPergunta implements Comparable<DetalheProdutoPergunta> {
    private final String titulo;
    private final String interessado;

    public DetalheProdutoPergunta(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.interessado = pergunta.getInteressado().getEmail();
    }

    @Override
    public int compareTo(DetalheProdutoPergunta o) {
        return this.titulo.compareTo(o.titulo);
    }
}
