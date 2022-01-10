package com.ecommerce.ecommercebe.exception;

public class EmailAddressExistsException extends RuntimeException{
    public EmailAddressExistsException(){
        super("Already Registered");
    }

    public EmailAddressExistsException(String message){
        super(message);
    }
}
