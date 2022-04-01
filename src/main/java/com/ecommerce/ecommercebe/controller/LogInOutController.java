package com.ecommerce.ecommercebe.controller;

import com.ecommerce.ecommercebe.pojo.request.LoginRequest;
import com.ecommerce.ecommercebe.pojo.response.LoginResponse;
import com.ecommerce.ecommercebe.pojo.response.LogoutResponse;
import com.ecommerce.ecommercebe.service.LogInOutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class LogInOutController {
    @Autowired
    private LogInOutService logInOutService;

    @PostMapping(
            value = "/login",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public LoginResponse loginUser(@Valid @RequestBody LoginRequest request){
        log.info("Request body: {}", request);
        return logInOutService.loginUser(request);
    }

    @PostMapping(
            value = "/logout",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public LogoutResponse logoutUser(@RequestHeader(value = "token") String token) {
        log.info("Token value: {}", token);
        return logInOutService.logoutUser(token);
    }
}
