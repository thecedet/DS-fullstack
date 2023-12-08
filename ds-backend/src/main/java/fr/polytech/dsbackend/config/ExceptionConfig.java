package fr.polytech.dsbackend.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import fr.polytech.dsbackend.dto.response.MessageDto;
import fr.polytech.dsbackend.exception.ResourceAlreadyExistException;
import fr.polytech.dsbackend.exception.ResourceBadRequestException;
import fr.polytech.dsbackend.exception.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody MessageDto internalServerError(Exception ex) {
        ex.printStackTrace();
        return MessageDto.builder().code("INTERNAL_ERROR").message("An internal error occurred").build();
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody MessageDto resourceNotFound(ResourceNotFoundException ex) {
        return MessageDto.builder().code("NOT_FOUND").message(ex.getMessage()).build();
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody MessageDto noresourceNotFound(NoResourceFoundException ex) {
        return MessageDto.builder().code("NOT_FOUND").message(ex.getMessage()).build();
    }

    @ExceptionHandler(value = ResourceAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody MessageDto resourceNotFound(ResourceAlreadyExistException ex) {
        return MessageDto.builder().code("RESOURCE_ALREADY_EXIST").message(ex.getMessage()).build();
    }

    @ExceptionHandler(value = ResourceBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody MessageDto badrequest(ResourceBadRequestException ex) {
        return MessageDto.builder().code("BAD_REQUEST").message(ex.getMessage()).build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody MessageDto badrequest(MethodArgumentNotValidException ex) {
        return MessageDto.builder().code("BAD_REQUEST").message(ex.getFieldError().getDefaultMessage()).build();
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody MessageDto badrequest(HttpMessageNotReadableException ex) {
        return MessageDto.builder().code("BAD_REQUEST").message(ex.getMessage()).build();
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody MessageDto badrequest(HttpRequestMethodNotSupportedException ex) {
        return MessageDto.builder().code("NOT_FOUND").message(ex.getMessage()).build();
    }

}
