package com.Hospital_System.demo.Error;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException e) {
        ApiError apiError = new ApiError();

        apiError.setMessage(e.getMessage());
        apiError.setStatusCode(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException e) {

        ApiError apiError = new ApiError();
        apiError.setMessage("Authentication failed "+e.getMessage());
        apiError.setStatusCode(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException e) {
        ApiError apiError = new ApiError();
        apiError.setMessage("Jwt token expired "+e.getMessage());
        apiError.setStatusCode(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException e) {
        ApiError apiError = new ApiError();
        apiError.setMessage("Access denied "+e.getMessage());
        apiError.setStatusCode(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> InternalServerError(Exception e) {
        ApiError apiError = new ApiError();
        apiError.setMessage("Internal server error "+e.getMessage());
        apiError.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        ApiError apiError = new ApiError();
        apiError.setMessage(errorMessage);
        apiError.setStatusCode(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

}
