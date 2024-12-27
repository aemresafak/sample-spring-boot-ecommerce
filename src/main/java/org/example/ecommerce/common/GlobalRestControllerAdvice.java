package org.example.ecommerce.common;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(exception = {HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.warn("HttpMessage could not be read",e);
        var error = "Malformed request body.";
        return Map.of("error", error);
    }

    @ExceptionHandler(exception = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        var parameterName = exception.getName();

        var error = "Method argument could not be converted to desired type.";
        return Map.of("error", error, "parameterName", parameterName);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var stringBuilder = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            stringBuilder.append("%s:%s ".formatted(fieldName, errorMessage));
        });

        return Map.of("error", "Validation error", "message", stringBuilder.toString());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String, String> handleNotFoundException(NotFoundException exception) {
        return Map.of("error",exception.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(BusinessException.class)
    public Map<String, String> handleBusinessException(BusinessException exception) {
        return Map.of("error", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JWTVerificationException.class)
    public Map<String, String> handleJWTVerificationException(JWTVerificationException exception) {
        log.info("Could not verify JWT", exception);
        return Map.of("error", exception.getMessage());
    }


}
