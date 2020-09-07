package com.jornadadev.mercadolivre.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class ValidationErrorHandler {

    private final MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ValidationErrorsOutputDto handleValidationError(MethodArgumentNotValidException exception) {
        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        final List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        return buildValidationErrors(fieldErrors, globalErrors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ValidationErrorsOutputDto handleValidationError(BindException exception) {
        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        final List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        return buildValidationErrors(fieldErrors, globalErrors);
    }

    private ValidationErrorsOutputDto buildValidationErrors(List<FieldError> fieldErrors, List<ObjectError> globalErrors) {
        ValidationErrorsOutputDto validationErrors = new ValidationErrorsOutputDto();
        globalErrors.forEach(error ->  validationErrors.addError(getErrorMessage(error)));
        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            validationErrors.addFieldError(error.getField(), errorMessage);
        });
        return validationErrors;
    }

    private String getErrorMessage(ObjectError error){
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }

}