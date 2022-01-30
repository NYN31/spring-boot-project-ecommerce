package com.ecommerce.ecommercebe.service.impl;

import com.ecommerce.ecommercebe.db.entity.UserEntity;
import com.ecommerce.ecommercebe.db.repository.UserRepository;
import com.ecommerce.ecommercebe.exception.NotFoundException;
import com.ecommerce.ecommercebe.exception.WalledIdExistsException;
import com.ecommerce.ecommercebe.pojo.request.UserProfileEditRequest;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.service.CommonUserFeaturesService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.Optional;

@Slf4j
@Service
public class CommonUserFeaturesServiceImpl implements CommonUserFeaturesService {
    @Autowired
    private UserRepository userRepository;

    @Value("${token.signature.secret.key.base64}")
    private String SECRETE_KEY;

    public CommonResponse editUserProfile(UserProfileEditRequest request, String token){
        log.info("Enter into edit user profile service");
        if(userRepository.findByWalletId(request.getWalletId()) != null){
            throw new WalledIdExistsException("User already exist with this wallet ID");
        }

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETE_KEY))
                .parseClaimsJws(token).getBody();

        request.setEmail((String) claims.get("emailAddress"));
        log.info("{}, {}", claims.get("emailAddress"), request);
        updateProfileIntoDatabase(request);

        CommonResponse response = new CommonResponse();
        response.setCode(200);
        response.setMessage("Edited successfully");
        return response;
    }

    private void updateProfileIntoDatabase(UserProfileEditRequest request){
        log.info("Enter into update database section");
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findByEmail(request.getEmail()));

        if(user.isPresent()){
            user.get().setName(request.getName());
            user.get().setAddress(request.getAddress());
            user.get().setCompanyName(request.getCompanyName());
            user.get().setWalletId(request.getWalletId());
            user.get().setImageUrl(request.getImageURL());

            userRepository.save(user.get());
        }
        else {
            throw new NotFoundException("Something went wrong");
        }
    }
}
