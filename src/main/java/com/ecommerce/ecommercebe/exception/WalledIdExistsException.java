package com.ecommerce.ecommercebe.exception;

public class WalledIdExistsException extends RuntimeException{
    public WalledIdExistsException(){
        super("Already Registered");
    }

    public WalledIdExistsException(String message){
        super(message);
    }
}
