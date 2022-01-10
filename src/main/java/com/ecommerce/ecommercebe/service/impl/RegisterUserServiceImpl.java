package com.ecommerce.ecommercebe.service.impl;

import com.ecommerce.ecommercebe.db.entity.UserEntity;
import com.ecommerce.ecommercebe.db.repository.UserRepository;
import com.ecommerce.ecommercebe.exception.EmailAddressExistsException;
import com.ecommerce.ecommercebe.exception.WalledIdExistsException;
import com.ecommerce.ecommercebe.pojo.request.RegisterRequest;
import com.ecommerce.ecommercebe.pojo.response.RegisterResponse;
import com.ecommerce.ecommercebe.service.RegisterUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterUserServiceImpl implements RegisterUserService {
    @Autowired
    private UserRepository userRepository;

    public RegisterResponse registerUser(RegisterRequest request, String userType){
        validatingUser(request);

        saveIntoDatabase(request, userType);

        RegisterResponse response = new RegisterResponse();
        response.setCode(200);
        response.setMessage("Successfully registered user as a " + userType);

        return response;
    }

    private void saveIntoDatabase(RegisterRequest request, String userType){
        log.info("Saving User in database");
        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .walletId(request.getWalletId())
                .userType(userType)
                .address(request.getAddress())
                .balance(request.getBalance())
                .companyName(request.getCompanyName())
                .imageUrl(request.getImageURL())
                .balance(request.getBalance())
                .build();
        userRepository.save(user);
    }

    private void validatingUser(RegisterRequest request){
        validateByEmailAddress(request.getEmail());
        validateByWalletId(request.getWalletId());
    }

    private void validateByEmailAddress(String email){
        if(userRepository.findByEmail(email) != null){
            throw new EmailAddressExistsException("Email address already exist");
        }
    }

    private void validateByWalletId(String walletId){
        if(userRepository.findByWalletId(walletId) != null){
            throw new WalledIdExistsException("Walled Id already exist");
        }
    }
}
