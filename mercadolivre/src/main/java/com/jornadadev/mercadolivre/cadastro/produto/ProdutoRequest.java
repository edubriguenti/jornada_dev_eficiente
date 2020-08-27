package com.jornadadev.mercadolivre.cadastro.produto;

import com.jornadadev.mercadolivre.config.ExistsId;
import com.jornadadev.mercadolivre.config.UniqueValue;
import com.jornadadev.mercadolivre.entity.Categoria;
import com.jornadadev.mercadolivre.entity.Produto;
import com.jornadadev.mercadolivre.entity.Usuario;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@ToString
public class ProdutoRequest {

    @NotBlank
    @UniqueValue(domainClass = Produto.class, fieldName = "nome")
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @Positive
    @NotNull
    private Integer quantidade;
    @Size(min = 3)
    @Valid
    private List<CaracteristicaRequest> caracteristicas;
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    @NotNull
    private Long idCategoria;

    public Produto toModel(EntityManager em, Usuario usuario) {
        final Categoria categoria = em.find(Categoria.class, idCategoria);
        return new Produto(nome, valor, quantidade, descricao, categoria, usuario, caracteristicas);
    }

    public List<String> buscaCaracteristicasIguais() {
        List<String> duplicates = caracteristicas.stream()
                .collect(Collectors.groupingBy(CaracteristicaRequest::getNome, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(p -> p.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return duplicates;
    }
}
