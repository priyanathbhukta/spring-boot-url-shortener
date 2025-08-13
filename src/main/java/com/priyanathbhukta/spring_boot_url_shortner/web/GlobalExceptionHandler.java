package com.priyanathbhukta.spring_boot_url_shortner.web;

import com.priyanathbhukta.spring_boot_url_shortner.domain.exceptions.ShortUrlNotFoundException;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ShortUrlNotFoundException.class)
    String shortUrlNotFoundException(ShortUrlNotFoundException ex){
        log.error("Short URL Not Found Exception: {}", ex.getMessage());
        return "error/404";
    }
}
