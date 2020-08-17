package com.jornadadev.casadocodigo.fechamentocompra;

import com.jornadadev.casadocodigo.entity.CupomDesconto;
import com.jornadadev.casadocodigo.repository.CupomDescontoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CupomDescontoValidator implements Validator {

    private final CupomDescontoRepository repository;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors())
            return;

        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) o;
        final Optional<String> possivelCupom = novaCompraRequest.getCupomDesconto();
        if (possivelCupom.isPresent()) {
            final CupomDesconto cupom = repository.getByCodigo(possivelCupom.get());
            if (!cupom.isValido()) {
                errors.rejectValue("cupomDesconto", null, "Cupom expirado.");
            }
        }
    }
}
