package com.ecommerce.ecommercebe.exception;

import lombok.Getter;

@Getter
public class Non200Exception extends CommonException {
    private Integer statusCode;
    public Non200Exception(Integer statusCode){
        this(statusCode, "Non 200 exception");
    }

    public Non200Exception(Integer statusCode, String message){
        super(message);
        this.statusCode = statusCode;
    }
}
