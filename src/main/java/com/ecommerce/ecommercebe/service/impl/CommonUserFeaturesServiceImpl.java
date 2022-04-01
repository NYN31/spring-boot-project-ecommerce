package com.ecommerce.ecommercebe.service.impl;

import com.ecommerce.ecommercebe.db.entity.ProductEntity;
import com.ecommerce.ecommercebe.db.entity.UserEntity;
import com.ecommerce.ecommercebe.db.repository.ProductRepository;
import com.ecommerce.ecommercebe.db.repository.UserRepository;
import com.ecommerce.ecommercebe.exception.CommonException;
import com.ecommerce.ecommercebe.exception.EmailAddressExistsException;
import com.ecommerce.ecommercebe.exception.NotFoundException;
import com.ecommerce.ecommercebe.exception.WalledIdExistsException;
import com.ecommerce.ecommercebe.pojo.request.UserProfileEditRequest;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.pojo.response.ProductListResponse;
import com.ecommerce.ecommercebe.pojo.response.ProductResponse;
import com.ecommerce.ecommercebe.service.CommonUserFeaturesService;
import com.ecommerce.ecommercebe.service.LogInOutService;
import com.ecommerce.ecommercebe.utility.utilClasses.ParseToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CommonUserFeaturesServiceImpl implements CommonUserFeaturesService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogInOutService logInOutService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ParseToken parseToken;

    @Value("${token.signature.secret.key.base64}")
    private String SECRETE_KEY;

    public CommonResponse editUserProfile(UserProfileEditRequest request, String token){
        UserEntity user = parseToken.getUser(token);

        log.info("Enter into edit user profile service");
        if(user.getWalletId().equals(request.getWalletId())
        && userRepository.findByWalletId(request.getWalletId()) != null){
            throw new WalledIdExistsException("User already exist with this wallet ID");
        }
        if(userRepository.findByEmail(request.getEmail()) != null) {
            throw new EmailAddressExistsException("User already exist with this email ID");
        }

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETE_KEY))
                .parseClaimsJws(token).getBody();

        request.setEmail((String) claims.get("emailAddress"));
        log.info("{}, {}", claims.get("emailAddress"), request);
        updateProfileIntoDatabase(request);
        var logoutResponse = logInOutService.logoutUser(token);
        log.info("Logout Response: {}", logoutResponse);
        CommonResponse response = new CommonResponse();
        response.setCode(200);
        response.setMessage("Edited successfully & logged out");
        return response;
    }

    @Override
    public ProductListResponse getAllProduct(String token) {
        log.info("Show all products");

        List<ProductEntity> products = productRepository.findAll();
        log.info("All products: {}", products);

        ProductListResponse response = new ProductListResponse();
        response.setCode(200);
        response.setMessage("Successfully fetch all products");
        response.setProducts(products);
        log.info("Product List Response: {}", response);
        return response;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        log.info("Show individual product");

        Optional<ProductEntity> product = productRepository.findById(id);
        if(!product.isPresent()) {
            throw new CommonException("Product not found");
        }
        ProductResponse response = new ProductResponse();
        response.setCode(200);
        response.setMessage("Product fetched successfully");
        response.setProduct(product.get());
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
