package com.jornadadev.casadocodigo.fechamentocompra;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VerificarDocumentoCpfCnpjValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors())
            return;

        NovaCompraRequest request = (NovaCompraRequest) o;
        if (!request.documentoValido()) {
            errors.rejectValue("documento", null, "Documento precisa ser um CPF ou CNPJ válido.");
        }
    }
}
