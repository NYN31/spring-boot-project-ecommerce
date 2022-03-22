package com.ecommerce.ecommercebe.service.impl;

import com.ecommerce.ecommercebe.db.entity.ProductEntity;
import com.ecommerce.ecommercebe.db.entity.UserEntity;
import com.ecommerce.ecommercebe.db.repository.ProductRepository;
import com.ecommerce.ecommercebe.db.repository.UserRepository;
import com.ecommerce.ecommercebe.exception.CommonException;
import com.ecommerce.ecommercebe.exception.NotFoundException;
import com.ecommerce.ecommercebe.pojo.request.ProductRequest;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.service.ProductFeatureService;
import com.ecommerce.ecommercebe.utility.utilClasses.PermissionDenied;
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
public class ProductFeatureServiceImpl implements ProductFeatureService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PermissionDenied permissionDenied;

    @Value("${token.signature.secret.key.base64}")
    public String SECRETE_KEY;

    @Value("${user.type.one}")
    public String buyer;

    @Value("${user.type.one}")
    public String seller;

    public CommonResponse addProduct(ProductRequest request, String token){
        UserEntity user = parseToken(token);

        permissionDenied.ForBuyer(user.getUserType());
        log.info("Add product: {}", productRepository.findByName(request.getName()));
        if(productRepository.findByName(request.getName()).isPresent()){
            throw new CommonException("The product with same name has already exist");
        }

        ProductEntity product = ProductEntity.builder()
                .name(request.getName())
                .sellerId(user.getId())
                .price(request.getPrice())
                .tag(request.getTag())
                .quantity(request.getQuantity())
                .rating(request.getRating())
                .build();
        productRepository.save(product);

        CommonResponse response = new CommonResponse();
        response.setCode(200);
        response.setMessage("Product added successfully");
        return response;
    }

    private UserEntity parseToken(String token){
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
