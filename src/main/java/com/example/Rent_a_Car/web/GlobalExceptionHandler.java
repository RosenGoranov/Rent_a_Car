package com.example.Rent_a_Car.web;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handelException(Exception ex,
                                             WebRequest request,
                                             HttpStatusCode status) {

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status code", status.value());

        responseBody.put("errors", ex.getMessage());
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
