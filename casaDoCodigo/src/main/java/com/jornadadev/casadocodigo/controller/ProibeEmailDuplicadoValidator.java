package com.jornadadev.casadocodigo.controller;

import com.jornadadev.casadocodigo.controller.dto.NovoAutorDto;
import com.jornadadev.casadocodigo.entity.Autor;
import com.jornadadev.casadocodigo.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProibeEmailDuplicadoValidator implements Validator {

    private final AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovoAutorDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NovoAutorDto request = (NovoAutorDto) o;
        final Optional<Autor> possivelAutor = autorRepository.findByEmail(request.getEmail());

        if (possivelAutor.isPresent()) {
            errors.rejectValue("email", null, "Já existe um outro autor com o mesmo e-mail " + request.getEmail());
        }
    }
}
