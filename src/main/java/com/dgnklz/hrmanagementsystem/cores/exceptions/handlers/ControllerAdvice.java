package com.dgnklz.hrmanagementsystem.cores.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    /*@ExceptionHandler(value = TokenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public TokenErrorMessage handleTokenException(TokenException exception) {
        return new TokenErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage());
    }*/
}