package com.ecommerce.ecommercebe.controller;

import com.ecommerce.ecommercebe.db.repository.ProductRepository;
import com.ecommerce.ecommercebe.pojo.request.ProductRequest;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.pojo.response.ProductResponse;
import com.ecommerce.ecommercebe.service.ProductFeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/products")
public class ProductFeatureController {
    @Autowired
    private ProductFeatureService productFeatureService;

    @PostMapping(value = "/add")
    public CommonResponse addProduct(
            @Valid @RequestBody ProductRequest request,
            @RequestHeader(value = "token") String token){
        log.info("Add product {}", request);
        return productFeatureService.addProduct(request, token);
    }
}
