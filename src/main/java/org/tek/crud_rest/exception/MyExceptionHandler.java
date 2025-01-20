package org.jsp.crud_rest.exception;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.jsp.crud_rest.helper.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @Autowired
    ResponseStructure<String> structure;

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseStructure<String>> handler(DataIntegrityViolationException exception) {
        structure.setMessage("Data cannot be saved");
        structure.setStatus(HttpStatus.BAD_REQUEST.value());
        structure.setData("Mobile number already exists");

        return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseStructure<String>> handler(NoSuchElementException exception) {
        structure.setMessage("Data not found");
        structure.setStatus(HttpStatus.NOT_FOUND.value());
        structure.setData(exception.getMessage());

        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseStructure<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                          .getAllErrors()
                          .stream()
                          .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                          .collect(Collectors.joining(", "));

        structure.setMessage("Validation failed");
        structure.setStatus(HttpStatus.BAD_REQUEST.value());
        structure.setData(errors);

        return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
    }
}
