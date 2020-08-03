package com.jornadadev.casadocodigo.fechamentocompra;

import com.jornadadev.casadocodigo.entity.Estado;
import com.jornadadev.casadocodigo.entity.Pais;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;

@Component
@AllArgsConstructor
public class EstadoPertenceAPaisValidator  implements Validator {

    private final EntityManager em;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors())
            return;

        NovaCompraRequest request = (NovaCompraRequest) o;
        final Pais pais = em.find(Pais.class, request.getPais());
        if (!request.temEstado()) {
            if (pais.possuiEstado()) {
                errors.rejectValue("estado", null, "É necessário escolher um estado para este país.");
            }
        } else {
            final Estado estado = em.find(Estado.class, request.getEstado());
            if (!estado.pertence(pais)){
                errors.rejectValue("estado", null, "Estado não pertence ao país.");
            }
        }
    }
}
