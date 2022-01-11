package com.ecommerce.ecommercebe.service;

import com.ecommerce.ecommercebe.pojo.request.JwtTokenRequest;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.pojo.response.JwtTokenResponse;
import org.springframework.stereotype.Service;

@Service
public interface JWTTokenService {
    JwtTokenResponse getJwtToken(JwtTokenRequest request);
    CommonResponse verifyToken(String token);
}
