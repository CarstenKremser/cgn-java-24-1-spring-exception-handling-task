package de.neuefische.springexceptionhandlingtask.dto;

public record ErrorMessage(
        int errorCode,
        String message
) {
}
