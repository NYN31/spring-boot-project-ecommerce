package com.ecommerce.ecommercebe.exception;

public class MalformedTokenException extends RuntimeException{
    public MalformedTokenException(){
        super("Jwt token format error");
    }

    public MalformedTokenException(String message){
        super(message);
    }
}
