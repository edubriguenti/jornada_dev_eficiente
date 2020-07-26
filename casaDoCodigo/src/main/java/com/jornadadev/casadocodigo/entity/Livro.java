package com.jornadadev.casadocodigo.entity;

import com.jornadadev.casadocodigo.config.UniqueValue;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@ToString
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"nome"}),
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
    private String sumario;
    @NotNull
    @Min(value = 20)
    private Double preco;
    @NotNull
    @Min(100)
    private Integer paginas;
    @NotEmpty
    @UniqueValue(fieldName = "isbn", domainClass = Livro.class)
    private String isbn;
    @Future
    private LocalDate dataPublicacao;
    @NotNull
    @ManyToOne
    private Categoria categoria;
    @NotNull
    @ManyToOne
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
