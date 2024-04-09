package de.neuefische.springexceptionhandlingtask.exceptions;

import de.neuefische.springexceptionhandlingtask.dto.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<ErrorMessage> handleNoSuchElementException(NoSuchElementException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/problem+json");

        return new ResponseEntity<>(
                new ErrorMessage(404, ex.getMessage()),
                headers,
                HttpStatus.NOT_FOUND
        );
    }
}
