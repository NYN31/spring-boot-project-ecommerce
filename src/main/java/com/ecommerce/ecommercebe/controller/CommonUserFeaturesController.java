package com.ecommerce.ecommercebe.controller;

import com.ecommerce.ecommercebe.pojo.request.UserProfileEditRequest;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.pojo.response.ProductListResponse;
import com.ecommerce.ecommercebe.pojo.response.ProductResponse;
import com.ecommerce.ecommercebe.service.CommonUserFeaturesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/commons")
public class CommonUserFeaturesController {
    @Autowired
    private CommonUserFeaturesService commonUserFeaturesService;

    @PostMapping(value = "/editProfile")
    public CommonResponse editUserFeature(
            @Valid @RequestBody UserProfileEditRequest request,
            @RequestHeader(value = "token") String token){
        log.info("Request body: {}, {}", request, token);
        return commonUserFeaturesService.editUserProfile(request, token);
    }

    @GetMapping(value = "/products")
    public ProductListResponse getAllProduct(@RequestHeader(value = "token") String token) {
        log.info("Enter into getAllProduct function for fetch products");
        return commonUserFeaturesService.getAllProduct(token);
    }

    @GetMapping(value = "/product/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        log.info("Fetch product by id {}", id);
        return commonUserFeaturesService.getProductById(id);
    }
}
