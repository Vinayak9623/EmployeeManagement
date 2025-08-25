package com.vsd.exception;

import com.vsd.common.ErrorResponse;
import com.vsd.exception.customeEx.EmployeeNotFoundException;
import com.vsd.exception.customeEx.LetterNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundEx(EmployeeNotFoundException ex, HttpServletRequest request){

        ErrorResponse response=new ErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setPath(request.getRequestURI());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LetterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLetterNotfoundEx(LetterNotFoundException ex,HttpServletRequest request){

        ErrorResponse response =new ErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setPath(request.getRequestURI());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }
}
