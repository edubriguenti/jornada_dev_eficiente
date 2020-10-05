package com.jornadadev.yfood.config

import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.lang.IllegalArgumentException
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ControllerAdviceRequestError() {

    private val messageNotReadableError = "Erro no payload."

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    fun handleValidationError(ex: MethodArgumentNotValidException) =
            ex.bindingResult.allErrors
                    .map {
                        if (it is FieldError) {
                            ErrorDto(it.field, it.defaultMessage)
                        } else {
                            ErrorDto("", it.defaultMessage)
                        }
                    }
                    .also {
                        println("BAD_REQUEST: $it")
                    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    fun handleValidationError(ex: BindException) =
            ex.bindingResult.allErrors
                    .map { ErrorDto(it.objectName, it.defaultMessage) }
                    .also {
                        println("BAD_REQUEST: $it")
                    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    fun handleValidationError(ex: IllegalArgumentException) =
            ErrorDto("", ex.message)
                    .also {
                        println("BAD_REQUEST: ${ex.message}")
                    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    fun handleValidationError(ex: MethodArgumentTypeMismatchException) =
            ErrorDto(ex.name, ex.message)
                    .also {
                        println("BAD_REQUEST: $it")
                    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    fun handleValidationError(ex: HttpMessageNotReadableException) =
            ErrorDto("Payload", messageNotReadableError)
                    .also {
                        println("BAD_REQUEST: $messageNotReadableError ${ex.message}")
                    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    fun handleValidationError(ex: ConstraintViolationException) =
            ex.constraintViolations.map { f -> ErrorDto(f.propertyPath.toString(), f.message) }
                    .also {
                        println("BAD_REQUEST: $it")
                    }
}