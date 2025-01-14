package com.jason.gtool.error;

import com.jason.gtool.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handlerException(Exception e) {
        System.out.println("全局异常捕获：" + e.getCause());
        return Result.error(e.getMessage());
    }
}

