package com.ecommerce.ecommercebe.utility.utilClasses;

import com.ecommerce.ecommercebe.db.entity.UserEntity;
import com.ecommerce.ecommercebe.db.repository.UserRepository;
import com.ecommerce.ecommercebe.exception.NotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Optional;

@Slf4j
@Component
public class ParseToken {
    @Autowired
    private UserRepository userRepository;

    @Value("${token.signature.secret.key.base64}")
    public String SECRETE_KEY;

    public UserEntity getUser(String token){
        log.info("Parsing jwt token in parseToken method");

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETE_KEY))
                .parseClaimsJws(token).getBody();

        Optional<UserEntity> user = Optional.ofNullable(
                userRepository.findByEmail((String) claims.get("emailAddress")));
        if(user.isPresent()) {
            return user.get();
        }else {
            throw new NotFoundException("User not found");
        }
    }
}
