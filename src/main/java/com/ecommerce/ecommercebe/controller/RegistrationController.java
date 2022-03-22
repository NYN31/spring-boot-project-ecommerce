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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/register")
public class RegistrationController {

    private final String USER_TYPE1 = "buyer";
    private final String USER_TYPE2 = "seller";

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private JWTTokenService jwtTokenService;

    @PostMapping(
            value = "/buyer",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RegisterResponse RegisterBuyer(@Valid @RequestBody RegisterRequest request){
        log.info("Buyer registration {}", request);
        return registerUserService.registerUser(request, USER_TYPE1);
    }

    @PostMapping(
            value = "/seller",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RegisterResponse RegisterSeller(@Valid @RequestBody RegisterRequest request){
        log.info("Seller registration {}", request);
        return registerUserService.registerUser(request, USER_TYPE2);
    }

    @PostMapping(
            value = "/test",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JwtTokenResponse test(@Valid @RequestBody JwtTokenRequest request){
        log.info("JwtTokenRequest: {}", request);
        return JwtTokenResponse.builder().token("xyzTestToken").build();
    }
}
