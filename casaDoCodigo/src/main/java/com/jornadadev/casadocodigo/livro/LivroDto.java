package com.jornadadev.casadocodigo.livro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jornadadev.casadocodigo.config.UniqueValue;
import com.jornadadev.casadocodigo.entity.Autor;
import com.jornadadev.casadocodigo.entity.Categoria;
import com.jornadadev.casadocodigo.entity.Livro;
import lombok.Getter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
public class LivroDto {

    @NotBlank
    @UniqueValue(fieldName = "titulo", domainClass = Livro.class)
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
    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;
    @NotNull
    private Categoria categoria;
    @NotNull
    private Autor autor;

    public Livro toModel() {
        return new Livro(titulo, resumo, sumario, preco, paginas, isbn, dataPublicacao, categoria, autor);
    }
}
