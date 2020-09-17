package com.jornadadev.mercadolivre.detalheproduto;

import com.jornadadev.mercadolivre.entity.Produto;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;

@Getter
public class DetalheProdutoView {
    private List<String> imagens;
    private String nome;
    private BigDecimal preco;
    private List<DetalheProdutoCaracteristica> caracteristicas;
    private String descricao;
    private Double mediaNotas;
    private Integer numeroNotas;
    private Set<Map<String,String>> opinioes;
    private Set<DetalheProdutoPergunta> perguntas;

    public DetalheProdutoView(Produto produto) {

        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.caracteristicas = produto.mapCaracteristicas(DetalheProdutoCaracteristica::new);
        this.imagens = produto.mapImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapPerguntas(pergunta -> new DetalheProdutoPergunta(pergunta));
        this.opinioes = produto.getOpinioes().mapeiaOpinioes(opiniao ->
                Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao()));
        this.mediaNotas = produto.getOpinioes().media();
        this.numeroNotas = produto.getOpinioes().total();
    }
}
