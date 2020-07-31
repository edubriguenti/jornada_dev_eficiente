package com.jornadadev.casadocodigo.estado;

import com.jornadadev.casadocodigo.config.ExistsId;
import com.jornadadev.casadocodigo.config.UniqueValue;
import com.jornadadev.casadocodigo.entity.Estado;
import com.jornadadev.casadocodigo.entity.Pais;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class EstadoDto {

    @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    private String nome;

    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Integer idPais;

    public Estado toModel(EntityManager em) {
        final Pais pais = em.find(Pais.class, this.idPais);
        return new Estado(nome, pais);
    }
}
