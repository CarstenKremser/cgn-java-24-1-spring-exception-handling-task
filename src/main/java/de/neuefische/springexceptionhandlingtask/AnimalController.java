package de.neuefische.springexceptionhandlingtask;

import de.neuefische.springexceptionhandlingtask.dto.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/animals")
@RestControllerAdvice
public class AnimalController {

    @GetMapping("{species}")
    String getAnimalSpecies(@PathVariable String species) {
        if (!species.equals("dog")) {
            throw new IllegalArgumentException("Only 'dog' is allowed");
        }
        return species;
    }

    @GetMapping
    String getAllAnimals() {
        throw new NoSuchElementException("No Animals found");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ErrorMessage>  handleIllegalArgumentException(IllegalArgumentException ex) {
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
