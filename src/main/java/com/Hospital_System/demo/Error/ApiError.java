package com.Hospital_System.demo.Error;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ApiError {
    @CreationTimestamp
    private LocalDateTime localDateTime=LocalDateTime.now();
    private HttpStatus statusCode;
    private String message;

}
