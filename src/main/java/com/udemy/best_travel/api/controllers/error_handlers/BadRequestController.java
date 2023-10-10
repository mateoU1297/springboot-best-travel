package com.udemy.best_travel.api.controllers.error_handlers;

import com.udemy.best_travel.api.models.response.BaseErrorResponse;
import com.udemy.best_travel.api.models.response.ErrorResponse;
import com.udemy.best_travel.api.models.response.ErrorsResponse;
import com.udemy.best_travel.util.exceptions.IdNotFoundException;
import com.udemy.best_travel.util.exceptions.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {

    @ExceptionHandler({IdNotFoundException.class, UsernameNotFoundException.class})
    public BaseErrorResponse handleIdNotFound(RuntimeException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleArgumentNotValid(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, String>();

        exception.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ErrorsResponse.builder()
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
