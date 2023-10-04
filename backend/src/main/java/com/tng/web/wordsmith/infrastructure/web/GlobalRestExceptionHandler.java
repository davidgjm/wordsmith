package com.tng.web.wordsmith.infrastructure.web;

import jakarta.annotation.Nullable;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.net.URI;

@Slf4j
@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        problemDetail.setType(buildProblemType("validation", ex));
        return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleValidationExceptions(ConstraintViolationException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        problemDetail.setType(buildProblemType("validation", ex));
        return super.handleExceptionInternal(ex, problemDetail, HttpHeaders.EMPTY, status, request);
    }


    @ExceptionHandler({DataIntegrityViolationException.class})
    public ProblemDetail handleDataExceptions(DataIntegrityViolationException ex, WebRequest request) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(buildProblemType("data", ex));
        return problemDetail;
    }

    private static URI buildProblemType(@Nullable String namespace, Throwable exception) {
        return URI.create(String.format("error://%s/%s", namespace, exception.getClass().getSimpleName()));
    }
}
