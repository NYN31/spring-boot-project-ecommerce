package com.ecommerce.ecommercebe.controller;

import com.ecommerce.ecommercebe.db.repository.ProductRepository;
import com.ecommerce.ecommercebe.pojo.request.ProductRequest;
import com.ecommerce.ecommercebe.pojo.response.ProductResponse;
import com.ecommerce.ecommercebe.service.ProductFeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController(value = "/products")
public class ProductFeatureController {
    @Autowired
    private ProductFeatureService productFeatureService;

    @PostMapping(
            value = "/add",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ProductResponse addProduct(
            @RequestBody ProductRequest request,
            @RequestHeader(value = "token") String token){
        log.info("Add product {}", request);
        return productFeatureService.addProduct(request, token);
    }
}
