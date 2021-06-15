package com.example.mutant.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IllegalArgumentExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String IllegalArgumentExceptionHandler(IllegalArgumentException e){
        return e.getMessage();
    }

}
