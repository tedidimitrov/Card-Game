package com.example.CardGame.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ErrorObject {

    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime dateTime;

}
