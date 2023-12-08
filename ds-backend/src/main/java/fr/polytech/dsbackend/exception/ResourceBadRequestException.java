package fr.polytech.dsbackend.exception;

public class ResourceBadRequestException extends RuntimeException{
    public ResourceBadRequestException(String message) {
        super(message);
    }
}
