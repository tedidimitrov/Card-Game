package com.example.CardGame.controller;

import com.example.CardGame.exceptions.ErrorObject;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GameExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ErrorObject> handleExceptions(ConstraintViolationException cve) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorObject errorObject =
                new ErrorObject(cve.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(errorObject, badRequest);
    }
}
