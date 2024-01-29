package me.dri.aiko.exception;

import jakarta.servlet.http.HttpServletRequest;
import me.dri.aiko.exception.entities.ExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class Thrower {

    private final String ERROR_MESSAGE_REQUEST = "Failed request";

    public ResponseEntity<ExceptionEntity> throwException(RuntimeException e, HttpServletRequest request, HttpStatus status) {
        String path = request.getRequestURI();
        me.dri.aiko.exception.entities.ExceptionEntity exceptionEntity = new me.dri.aiko.exception.entities.ExceptionEntity(
                new Date(),
                ERROR_MESSAGE_REQUEST,
                e.getMessage(),
                status.value(),
                path
        );
        return org.springframework.http.ResponseEntity.status(status).body(exceptionEntity);
    }
}
