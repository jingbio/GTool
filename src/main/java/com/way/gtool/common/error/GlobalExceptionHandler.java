package com.way.gtool.common.error;

import com.way.gtool.common.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handlerException(Exception e) {
        System.out.println("全局异常捕获：" + e.getMessage());
        if (e instanceof NoResourceFoundException) {
            return Result.get(404, "资源未找到", null);
        }
        return Result.error(e.getMessage());
    }
}

