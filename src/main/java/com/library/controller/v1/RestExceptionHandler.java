package com.library.controller.v1;

import com.library.exeptions.LibraryExeception;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(LibraryExeception.class)
    public ProblemDetail handleLibraryException(LibraryExeception e){
        return e.toProblemDetail();
    }
}
