package com.ecommerce.ecommercebe.controller;

import com.ecommerce.ecommercebe.pojo.request.JwtTokenRequest;
import com.ecommerce.ecommercebe.pojo.request.RegisterRequest;
import com.ecommerce.ecommercebe.pojo.response.JwtTokenResponse;
import com.ecommerce.ecommercebe.pojo.response.RegisterResponse;
import com.ecommerce.ecommercebe.service.JWTTokenService;
import com.ecommerce.ecommercebe.service.RegisterUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController(value = "/register")
public class RegistrationController {
    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private JWTTokenService jwtTokenService;

    @PostMapping(
            value = "/buyer",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RegisterResponse RegisterBuyer(@RequestBody RegisterRequest request){
        log.info("Buyer registration {}", request);
        return registerUserService.registerUser(request, "buyer");
    }

    @PostMapping(
            value = "/seller",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RegisterResponse RegisterSeller(@RequestBody RegisterRequest request){
        log.info("Seller registration {}", request);
        return registerUserService.registerUser(request, "seller");
    }

    @PostMapping(
            value = "/test",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JwtTokenResponse test(@RequestBody JwtTokenRequest request){
        log.info("JwtTokenRequest: {}", request);
        return jwtTokenService.getJwtToken(request);
    }
}
