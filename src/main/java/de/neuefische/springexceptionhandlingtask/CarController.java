package de.neuefische.springexceptionhandlingtask;

import de.neuefische.springexceptionhandlingtask.dto.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cars")
@RestControllerAdvice
public class CarController {

    @GetMapping("{brand}")
    String getCarBrand(@PathVariable String brand) {
        if (!brand.equals("porsche")) {
            throw new IllegalArgumentException("Only 'porsche' allowed");
        }
        return brand;
    }

    @GetMapping
    String getAllCars() {
        throw new NoSuchElementException("No Cars found");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/problem+json");

        return new ResponseEntity<>(
                new ErrorMessage(400, ex.getMessage()),
                headers,
                HttpStatus.BAD_REQUEST);
    }

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
