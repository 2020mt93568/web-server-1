package com.example.todo.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import com.example.todo.controller.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = {AuthController.class, TodoController.class, UserController.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object object, HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest) {

        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        return new ResponseEntity<>(errors, httpStatus);

    }

    @ExceptionHandler({ConstraintViolationException.class, GenericException.class})
    public ResponseEntity<Object> handleBusinessException(Exception exception, WebRequest webRequest) {

        Map<String, String> errors = new HashMap<>();
        if(exception instanceof  ConstraintViolationException) {
            errors.put("message", exception.getMessage());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else if(exception instanceof GenericException) {
            errors.put("message", exception.getMessage());
            return new ResponseEntity<>(errors, ((GenericException) exception).getHttpStatus());
        } else {
            errors.put("message", exception.getMessage());
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest) {

        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

}
