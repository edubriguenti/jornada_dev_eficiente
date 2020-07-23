package com.jornadadev.casadocodigo.config;

import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class ErroDto {
    private String field;
    private String message;

    ErroDto(FieldError fieldError) {
        field = fieldError.getField();
        message = fieldError.getDefaultMessage();
    }

}
