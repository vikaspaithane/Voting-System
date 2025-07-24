package com.example.Crud.Operation.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserException(UserNotFoundException e, HttpServletRequest request) {
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCandidateException(CandidateNotFoundException e, HttpServletRequest request) {
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    // Common method for reusable error format
    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status, HttpServletRequest request) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("status", status.value());
        errorMap.put("error", status.getReasonPhrase());
        errorMap.put("message", message);
        errorMap.put("path", request.getRequestURI());

        return new ResponseEntity<>(errorMap, status);
    }
}
