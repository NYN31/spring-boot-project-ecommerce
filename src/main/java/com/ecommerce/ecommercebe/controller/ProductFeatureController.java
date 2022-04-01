package com.ecommerce.ecommercebe.controller;

import com.ecommerce.ecommercebe.db.repository.ProductRepository;
import com.ecommerce.ecommercebe.pojo.request.ProductRequest;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.pojo.response.ProductResponse;
import com.ecommerce.ecommercebe.pojo.response.ResultResponse;
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

    @PostMapping(value = "/update")
    public CommonResponse updateProduct(
            @Valid @RequestBody ProductRequest request,
            @RequestHeader(value = "token") String token){
        log.info("update product {}", request);
        return productFeatureService.updateProduct(request, token);
    }

    @PostMapping(value = "/delete/{id}")
    public CommonResponse deleteProduct(
            @PathVariable Long id,
            @RequestHeader(value = "token") String token){
        log.info("Delete product with productId: {}", id);
        return productFeatureService.deleteProduct(id, token);
    }

    @GetMapping(value = "/seller-products")
    public ResultResponse sellerOwnProduct(
            @RequestHeader(value = "token") String token) {
        log.info("Seller own product for sale");
        return productFeatureService.sellerOwnProduct(token);
    }
}
