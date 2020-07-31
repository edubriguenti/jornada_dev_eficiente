package com.jornadadev.casadocodigo.pais;

import com.jornadadev.casadocodigo.config.UniqueValue;
import com.jornadadev.casadocodigo.entity.Pais;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PaisDto {

    @UniqueValue(fieldName = "nome", domainClass = Pais.class)
    @NotBlank
    private String nome;

    public Pais toModel() {
        Pais pais = new Pais(this.nome);
        return pais;
    }
}
