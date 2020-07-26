package com.jornadadev.casadocodigo.categoria;

import com.jornadadev.casadocodigo.config.UniqueValue;
import com.jornadadev.casadocodigo.entity.Categoria;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CategoriaDto {

    @NotBlank
    @UniqueValue(domainClass=Categoria.class, fieldName = "nome")
    private String nome;

    public Categoria toModel() {
        Categoria categoria = new Categoria(nome);
        return categoria;
    }
}
