package org.example.springsecuritypractice.exception;


import org.example.springsecuritypractice.utils.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static org.example.springsecuritypractice.utils.GenericResponse.GenerateHttpResponse;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponse<?> handleConflict(
            MethodArgumentNotValidException ex, WebRequest webRequest) {
        return new HttpResponse<>(HttpStatus.BAD_REQUEST,
                ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage());
    }
}
