package com.jornadadev.mercadolivre.config;

import lombok.Getter;

@Getter
public class FieldErrorOutputDto {

    private String field;
    private String message;

    public FieldErrorOutputDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

}
