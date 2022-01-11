package com.ecommerce.ecommercebe.service.impl;

import com.ecommerce.ecommercebe.db.entity.TokenEntity;
import com.ecommerce.ecommercebe.db.repository.TokenRepository;
import com.ecommerce.ecommercebe.db.repository.UserRepository;
import com.ecommerce.ecommercebe.exception.ExpireTokenException;
import com.ecommerce.ecommercebe.exception.InvalidTokenException;
import com.ecommerce.ecommercebe.pojo.request.JwtTokenRequest;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.pojo.response.JwtTokenResponse;
import com.ecommerce.ecommercebe.service.JWTTokenService;
import com.ecommerce.ecommercebe.utility.enums.TokenStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class JWTTokenServiceImpl implements JWTTokenService {
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${token.signature.secret.key.base64}")
    public String SECRETE_KEY;
    @Value("${token.time.to.live.in.millis}")
    public Long ttlMillis;
    @Value("${token.issuer}")
    public String issuer;
    @Value("${token.subject}")
    public String subject;

    public JwtTokenResponse getJwtToken(JwtTokenRequest request) {
        log.info("Enter into getJwtToken function.");
        String walletId = userRepository.findByEmail(request.getEmail()).getWalletId();
        String token = getToken(request.getEmail(), walletId);

        JwtTokenResponse response = new JwtTokenResponse();
        response.setCode(200);
        response.setMessage("Token created successfully");
        response.setToken(token);
        return response;
    }

    private String getToken(String email, String walletNumber) {
        log.info("Enter into getToken function.");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String id = UUID.randomUUID().toString();

        Long nowMilliSecond = System.currentTimeMillis();
        Date issuedTime = new Date(nowMilliSecond); // issued time here
        Long expMilliSecond = nowMilliSecond + ttlMillis;
        Date expirationTime = new Date(expMilliSecond); // expired time here

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRETE_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Token building
        String token = Jwts.builder()
                .setId(id)
                .setIssuedAt(issuedTime)
                .setExpiration(expirationTime)
                .setSubject(subject)
                .setIssuer(issuer)
                .claim("walletId", walletNumber)
                .claim("emailAddress", email)
                .signWith(signatureAlgorithm, signingKey).compact();

        TokenEntity tokenEntity = TokenEntity.builder()
                .tokenId(id)
                .token(token)
                .createdAt(issuedTime)
                .expiredAt(expirationTime)
                .status(TokenStatus.ACTIVE)
                .walletId(walletNumber)
                .email(email)
                .build();
        tokenRepository.save(tokenEntity);

        return token;
    }

    public CommonResponse verifyToken(String token){
        log.info("Enter into verifyToken function.");
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETE_KEY))
                    .parseClaimsJws(token).getBody();

            String tokenId = (String) claims.getId();
            String walletId = (String) claims.get("walletId");

//            String issuer = (String) claims.getIssuer();
//            Date issued = (Date) claims.getIssuedAt();
//            Date expiration = (Date) claims.getExpiration();
//            String subject = (String) claims.getSubject();
//            String emailAddress = (String) claims.get("emailAddress");


            Optional<TokenEntity> tokenDetails =
                    Optional.ofNullable(tokenRepository.findByTokenIdAndWalletId(tokenId, walletId));
            if(!tokenDetails.isPresent() || tokenDetails.get().getStatus() == TokenStatus.INACTIVE) {
                throw new InvalidTokenException("Invalid token");
            }

            CommonResponse response = new CommonResponse();
            response.setCode(200);
            response.setMessage("Token verified successfully");
            return response;
        } catch(ExpiredJwtException e) {
            throw new ExpireTokenException("Expired token");
        } catch(Exception e) {
            throw new InvalidTokenException("Invalid token");
        }
    }

}
