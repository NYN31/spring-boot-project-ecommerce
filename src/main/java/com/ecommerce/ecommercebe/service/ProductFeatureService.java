package com.ecommerce.ecommercebe.service;

import com.ecommerce.ecommercebe.pojo.request.ProductRequest;
import com.ecommerce.ecommercebe.pojo.response.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductFeatureService {
    ProductResponse addProduct(ProductRequest request, String token);
}
