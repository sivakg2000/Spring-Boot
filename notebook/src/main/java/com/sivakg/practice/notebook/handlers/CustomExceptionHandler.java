package com.sivakg.practice.notebook.handlers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        //return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
        Map<String,Object> objectBody=new LinkedHashMap<>();
        objectBody.put("Current Timestamp",new Date());
        objectBody.put("Status",new Date());
        List<String> exceptionErrors=ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        objectBody.put("Errors",exceptionErrors);
        return new ResponseEntity<>(objectBody,status);

    }


    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException ex) {
        Map<String,Object> objectBody=new LinkedHashMap<>();
        objectBody.put("Current Timestamp",new Date());
        objectBody.put("Status",new Date());
        objectBody.put("Errors","Duplicate key error occurred. Please provide a valid value.");
        return new ResponseEntity<>(objectBody,HttpStatus.CONFLICT);
    }


}
