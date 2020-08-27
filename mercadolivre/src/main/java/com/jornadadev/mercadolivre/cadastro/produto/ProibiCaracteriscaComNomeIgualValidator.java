package com.jornadadev.mercadolivre.cadastro.produto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class ProibiCaracteriscaComNomeIgualValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        ProdutoRequest request = (ProdutoRequest) o;
        final List<String> caracteristicasIguais = request.buscaCaracteristicasIguais();
        if (caracteristicasIguais.size() > 0) {
            errors.rejectValue("caracteristicas", null, "Olha, você tem caracteristicas iguais: "+caracteristicasIguais);
        }

    }
}
