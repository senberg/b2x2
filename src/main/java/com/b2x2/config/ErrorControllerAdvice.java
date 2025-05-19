package com.b2x2.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Slf4j
@ControllerAdvice
public class ErrorControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<?> handleNoResourceFoundException(HttpServletRequest request, NoResourceFoundException ignored) {
        if (request.getHeader("Hx-Request") != null) {
            return ResponseEntity.status(NO_CONTENT).header("Hx-Redirect", "/").build();
        } else {
            return ResponseEntity.status(FOUND).header("location", "/").build();
        }
    }

    @ExceptionHandler
    public ResponseEntity<?> handleThrowable(HttpServletRequest request, Throwable t) {
        log.error("Exception during request to {}", request.getRequestURI(), t);

        if (request.getHeader("Hx-Request") != null) {
            return ResponseEntity.status(NO_CONTENT).header("Hx-Redirect", "/").build();
        } else {
            return ResponseEntity.status(FOUND).header("location", "/").build();
        }
    }
}
