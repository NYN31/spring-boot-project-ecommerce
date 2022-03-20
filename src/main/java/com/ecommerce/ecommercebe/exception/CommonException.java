package com.ecommerce.ecommercebe.exception;

public class CommonException extends RuntimeException {
    public CommonException(){
        super();
    }
    public CommonException(String message){
        super(message);
    }
}
