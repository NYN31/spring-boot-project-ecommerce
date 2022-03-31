package com.ecommerce.ecommercebe.service.impl;

import com.ecommerce.ecommercebe.db.entity.ProductEntity;
import com.ecommerce.ecommercebe.db.entity.UserEntity;
import com.ecommerce.ecommercebe.db.repository.ProductRepository;
import com.ecommerce.ecommercebe.db.repository.UserRepository;
import com.ecommerce.ecommercebe.exception.CommonException;
import com.ecommerce.ecommercebe.exception.NotFoundException;
import com.ecommerce.ecommercebe.pojo.request.ProductRequest;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.pojo.response.ResultResponse;
import com.ecommerce.ecommercebe.service.ProductFeatureService;
import com.ecommerce.ecommercebe.utility.utilClasses.ParseToken;
import com.ecommerce.ecommercebe.utility.utilClasses.PermissionDenied;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ProductFeatureServiceImpl implements ProductFeatureService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PermissionDenied permissionDenied;

    @Autowired
    private ParseToken parseToken;

    @Value("${token.signature.secret.key.base64}")
    public String SECRETE_KEY;

    @Value("${user.type.one}")
    public String buyer;

    @Value("${user.type.one}")
    public String seller;

    public CommonResponse addProduct(ProductRequest request, String token){
        UserEntity user = parseToken.getUser(token);

        permissionDenied.ForBuyer(user.getUserType());
        log.info("Add product: {}", request.getName());
        if(productRepository.findByName(request.getName()).isPresent()){
            throw new CommonException("The product with same name has already exist");
        }

        ProductEntity product = ProductEntity.builder()
                .productId(request.getName() + "@" + UUID.randomUUID() + "@")
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

    @Override
    public CommonResponse updateProduct(ProductRequest request, String token){
        UserEntity user = parseToken.getUser(token);

        permissionDenied.ForBuyer(user.getUserType());
        log.info("update productId is: {}", request.getProductId());

        Optional<ProductEntity> product = productRepository.findByProductId(request.getProductId());

        if(product.isPresent()) {

            product.get().setName(request.getName());
            product.get().setPrice(request.getPrice());
            product.get().setQuantity(request.getQuantity());
            product.get().setRating(request.getRating());
            product.get().setSellerId(user.getId());
            product.get().setTag(request.getTag());
            productRepository.save(product.get());
            log.info("Product updated successfully, {}", product.get());

            CommonResponse response = new CommonResponse();
            response.setCode(200);
            response.setMessage("Product updated successfully");
            return response;
        }
        throw new CommonException("Product not found");
    }

    @Override
    public CommonResponse deleteProduct(Long id, String token){
        UserEntity user = parseToken.getUser(token);

        permissionDenied.ForBuyer(user.getUserType());
        Optional<ProductEntity> product = productRepository.findById(id);
        if(!product.isPresent()) {
            throw new CommonException("Product not found");
        }

        productRepository.deleteById(id);
        CommonResponse response = new CommonResponse();
        response.setCode(200);
        response.setMessage("Product deleted successfully");
        return response;
    }

    @Override
    public ResultResponse sellerOwnProduct(String token) {
        UserEntity user = parseToken.getUser(token);

        permissionDenied.ForBuyer(user.getUserType());
        List<ProductEntity> productList = productRepository.findBySellerId(user.getId());

        return new ResultResponse(0, productList);
    }
}
