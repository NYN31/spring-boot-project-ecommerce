package com.ecommerce.ecommercebe.service.impl;

import com.ecommerce.ecommercebe.db.entity.TokenEntity;
import com.ecommerce.ecommercebe.db.entity.UserEntity;
import com.ecommerce.ecommercebe.db.repository.TokenRepository;
import com.ecommerce.ecommercebe.db.repository.UserRepository;
import com.ecommerce.ecommercebe.exception.NotFoundException;
import com.ecommerce.ecommercebe.pojo.request.JwtTokenRequest;
import com.ecommerce.ecommercebe.pojo.request.LoginRequest;
import com.ecommerce.ecommercebe.pojo.response.JwtTokenResponse;
import com.ecommerce.ecommercebe.pojo.response.LoginResponse;
import com.ecommerce.ecommercebe.pojo.response.LogoutResponse;
import com.ecommerce.ecommercebe.service.JWTTokenService;
import com.ecommerce.ecommercebe.service.LogInOutService;
import com.ecommerce.ecommercebe.utility.enums.TokenStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogInOutServiceImpl implements LogInOutService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JWTTokenService jwtTokenService;

    public LoginResponse loginUser(LoginRequest request){
        validateUserByEmailAndPassword(request.getEmail(), request.getPassword());

        JwtTokenRequest jwtTokenRequest
                = JwtTokenRequest.builder()
                .email(request.getEmail())
                .build();
        JwtTokenResponse jwtTokenResponse
                = jwtTokenService.getJwtToken(jwtTokenRequest);

        LoginResponse logInResponse = new LoginResponse();
        logInResponse.setCode(200);
        logInResponse.setMessage("Log in successful");
        logInResponse.setToken(jwtTokenResponse.getToken());

        return logInResponse;
    }

    public LogoutResponse logoutUser(String token){
        jwtTokenService.verifyToken(token);

        TokenEntity tokenEntity = tokenRepository.findByToken(token);
        tokenEntity.setStatus(TokenStatus.INACTIVE);
        tokenRepository.save(tokenEntity);

        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setCode(200);
        logoutResponse.setMessage("Logout successful");
        return logoutResponse;
    }

    private void validateUserByEmailAndPassword(String email, String password){
        UserEntity userEntity = userRepository.findByEmail(email);
        log.info("{}, {}", userEntity.getPassword(), password);
        if(userEntity == null){
            throw new NotFoundException("Incorrect email address");
        }

        if(!userEntity.getPassword().equals(password)){
            throw new NotFoundException("Incorrect password");
        }
    }
}
