package com.ecommerce.ecommercebe.service;

import com.ecommerce.ecommercebe.pojo.request.RegisterRequest;
import com.ecommerce.ecommercebe.pojo.response.RegisterResponse;

public interface RegisterUserService {
    RegisterResponse registerUser(RegisterRequest request,  String userType);
}
