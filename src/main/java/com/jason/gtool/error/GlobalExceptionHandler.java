package com.jason.gtool.error;

import com.jason.gtool.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handlerException(Exception e) {
        log.error("全局异常捕获：" + e.getCause());
        return Result.error(e.getMessage());
    }
}

