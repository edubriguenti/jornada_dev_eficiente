package com.jornadadev.casadocodigo.livro.detalhes;

import com.jornadadev.casadocodigo.entity.Livro;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Getter
public class DetalheLivroDto {

    private String isbn;
    private Integer paginas;
    private BigDecimal preco;
    private String resumo;
    private String sumario;
    private DetalheAutorDto autor;
    private String titulo;
    private String dataPublicacao;


    public DetalheLivroDto(Livro livro) {
        this.titulo = livro.getTitulo();
        this.autor = new DetalheAutorDto(livro.getAutor());
        this.isbn = livro.getIsbn();
        this.paginas = livro.getPaginas();
        this.preco = livro.getPreco();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.dataPublicacao = livro.getDataPublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
