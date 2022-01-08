package com.ecommerce.ecommercebe.controller;

import com.ecommerce.ecommercebe.pojo.request.RegisterRequest;
import com.ecommerce.ecommercebe.pojo.response.RegisterResponse;
import com.ecommerce.ecommercebe.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RegistrationController {
    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping(
            value = "/buyer/register",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RegisterResponse RegisterBuyer(@RequestBody RegisterRequest request){
        return registerUserService.registerUser(request, "buyer");
    }

    @PostMapping(
            value = "/seller/register",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RegisterResponse RegisterSeller(@RequestBody RegisterRequest request){
        return registerUserService.registerUser(request, "seller");
    }
}
