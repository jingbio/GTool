package com.jason.gtool.error;

import com.jason.gtool.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handlerException(Exception e) {
        e.printStackTrace();
        return Result.error(e.getMessage());
    }
}

