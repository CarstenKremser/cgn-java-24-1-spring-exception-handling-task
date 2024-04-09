package de.neuefische.springexceptionhandlingtask.dto;

import java.time.Instant;

public record ErrorMessage(
        int errorCode,
        String message,
        Instant timestamp
) {

    public ErrorMessage(int errorCode, String message) {
        this(errorCode, message, Instant.now());
    }
}
