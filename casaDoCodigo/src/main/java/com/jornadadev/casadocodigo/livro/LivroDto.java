package com.jornadadev.casadocodigo.livro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jornadadev.casadocodigo.config.ExistsId;
import com.jornadadev.casadocodigo.config.UniqueValue;
import com.jornadadev.casadocodigo.entity.Autor;
import com.jornadadev.casadocodigo.entity.Categoria;
import com.jornadadev.casadocodigo.entity.Livro;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
public class LivroDto {

    @NotBlank
    @UniqueValue(fieldName = "titulo", domainClass = Livro.class)
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
    @UniqueValue(fieldName = "isbn", domainClass = Livro.class)
    private String isbn;
    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;
    @NotNull
    @ExistsId(fieldName = "id", domainClass = Categoria.class)
    private Integer idCategoria;
    @NotNull
    @ExistsId(fieldName= "id", domainClass = Autor.class)
    private Integer idAutor;

    public Livro toModel(EntityManager em) {
        Categoria categoria = em.find(Categoria.class, idCategoria);
        Autor autor = em.find(Autor.class, idAutor);
        Assert.state(categoria != null, "A categoria está nula: "+ idCategoria);
        Assert.state(autor != null, "O autor está nulo: "+ idAutor);
        Livro livro = new Livro(titulo, resumo, sumario, preco, paginas, isbn, dataPublicacao, categoria, autor);
        return  livro;
    }
}
