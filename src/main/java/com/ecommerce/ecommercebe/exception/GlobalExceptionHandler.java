package com.ecommerce.ecommercebe.exception;

import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CommonResponse handleGenericException(Exception ex){
        CommonResponse response = new CommonResponse();
        response.setMessage(ex.getMessage());
        return response;
    }
}