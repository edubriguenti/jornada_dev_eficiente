package com.jornadadev.casadocodigo.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@ToString
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo"}),
        @UniqueConstraint(columnNames = {"isbn"})})
public class Livro {

    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String resumo;
    @NotBlank
    private String sumario;
    @NotNull
    @Min(value = 20)
    private Double preco;
    @NotNull
    @Min(100)
    private Integer paginas;
    @NotEmpty
    private String isbn;
    @Future
    @NotNull
    private LocalDate dataPublicacao;

    @NotNull
    @ManyToOne
    @Valid
    private Categoria categoria;

    @NotNull
    @ManyToOne
    @Valid
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
                 @NotNull @Min(value = 20) Double preco, @NotNull @Min(100) Integer paginas,
                 @NotEmpty String isbn, @Future LocalDate dataPublicacao, @NotNull Categoria categoria,
                 @NotNull Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.paginas = paginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }
}
