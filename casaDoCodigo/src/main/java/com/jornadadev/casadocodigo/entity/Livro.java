package com.jornadadev.casadocodigo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@ToString
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo"}),
        @UniqueConstraint(columnNames = {"isbn"})})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private BigDecimal preco;
    @NotNull
    @Min(100)
    private Integer paginas;
    @NotEmpty
    @EqualsAndHashCode.Include
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
                 @NotNull @Min(value = 20) BigDecimal preco, @NotNull @Min(100) Integer paginas,
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
