package com.jornadadev.mercadolivre.cadastro.categoria;

import com.jornadadev.mercadolivre.config.ExistsId;
import com.jornadadev.mercadolivre.config.UniqueValue;
import com.jornadadev.mercadolivre.entity.Categoria;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
public class CategoriaRequest {

    @NotEmpty
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    @Positive
    private Long idCategoriaMae;

    public Categoria toModel(EntityManager em) {
        final Categoria categoria = new Categoria(nome);
        if (idCategoriaMae != null) {
            categoria.setCategoriaMae(em.find(Categoria.class, idCategoriaMae));
        }
        return categoria;
    }
}
