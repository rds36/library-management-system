package com.rds.librarymanagementsystem.controller;

import com.rds.librarymanagementsystem.exception.BookStatusIdentifierException;
import com.rds.librarymanagementsystem.exception.DateFormatNotValidException;
import com.rds.librarymanagementsystem.model.ErrorResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        List<ObjectError> listObjectErrors = ex.getBindingResult().getAllErrors();

        listObjectErrors.forEach(objectError -> errors.add(objectError.getDefaultMessage()));

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundExceptionHandler(EntityNotFoundException ex){
        List<String> error = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(error), HttpStatus.NOT_FOUND); // Handle if entity not found
    }

    @ExceptionHandler(DateFormatNotValidException.class)
    public ResponseEntity<Object> dateTimeParseExceptionHandler(DateFormatNotValidException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> duplicateKeyExceptionHandler(DuplicateKeyException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookStatusIdentifierException.class)
    public ResponseEntity<Object> bookStatusIdentifierExceptionHandler(BookStatusIdentifierException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }
}
