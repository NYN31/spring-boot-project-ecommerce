package com.ecommerce.ecommercebe.service;

import com.ecommerce.ecommercebe.pojo.request.LoginRequest;
import com.ecommerce.ecommercebe.pojo.response.LoginResponse;
import com.ecommerce.ecommercebe.pojo.response.LogoutResponse;
import org.springframework.stereotype.Service;

@Service
public interface LogInOutService {
    LoginResponse loginUser(LoginRequest request);
    LogoutResponse logoutUser(String token);
}
