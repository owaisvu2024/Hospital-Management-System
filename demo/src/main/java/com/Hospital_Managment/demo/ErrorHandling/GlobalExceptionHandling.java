package com.Hospital_Managment.demo.ErrorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class GlobalExceptionHandling {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)

   public Map<String,String> handleException(MethodArgumentNotValidException ex){
        Map<String,String> map = new HashMap<>();
       ex.getBindingResult().getFieldErrors().forEach((error)->{

           String fieldName=error.getField();
           String fieldValue=error.getDefaultMessage();
           map.put(fieldName,fieldValue);



       });
       return map;
   }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(Exception.class)
   public Map<String,String> handleException(Exception ex){
        Map<String,String> map = new HashMap<>();
       map.put("message",ex.getMessage());
       return map;
   }
}
