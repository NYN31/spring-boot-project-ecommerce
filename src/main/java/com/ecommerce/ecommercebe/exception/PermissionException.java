package com.ecommerce.ecommercebe.exception;

public class PermissionException extends RuntimeException{
    public PermissionException(){
        super("You don't have permission");
    }

    public PermissionException(String message){
        super(message);
    }
}
