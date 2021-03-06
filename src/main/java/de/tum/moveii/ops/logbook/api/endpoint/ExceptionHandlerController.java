package de.tum.moveii.ops.logbook.api.endpoint;

import de.tum.moveii.ops.logbook.api.message.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

/**
 * Created by Alexandru Obada on 01/02/17.
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleBadRequest(MethodArgumentNotValidException methodArgumentException) {
        log.warn("Malformed message has been received: {}", methodArgumentException);
        String errorMessage = getErrorMessage(methodArgumentException.getBindingResult());
        return new ErrorMessage(errorMessage);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorMessage handleBindingException(BindException bindException) {
        log.warn("Invalid message has been received: {}", bindException);
        String errorMessage = getErrorMessage(bindException.getBindingResult());
        return new ErrorMessage(errorMessage);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorMessage malformedMessage(HttpMessageNotReadableException exception) {
        log.warn("Malformed message has been received: {}", exception);
        return new ErrorMessage("Malformed payload has been received");
    }

    @ResponseStatus(METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorMessage handleMethodNotAllowed(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        log.warn("Unexpected request: ", exception);
        return new ErrorMessage(String.format("Method <%s> not allowed at path <%s>", exception.getMethod(), request.getRequestURI()));
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorMessage handleServerSideError(Exception exception) {
        log.error("Unexpected error", exception);
        return new ErrorMessage("server side error");
    }

    private String getErrorMessage(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(error -> (FieldError) error)
                .map(error -> String.format("%s %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.joining("\n"));
    }
}