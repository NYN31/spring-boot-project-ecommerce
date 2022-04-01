package com.ecommerce.ecommercebe.service;

import com.ecommerce.ecommercebe.pojo.request.ProductRequest;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.pojo.response.ProductResponse;
import com.ecommerce.ecommercebe.pojo.response.ResultResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductFeatureService {
    CommonResponse addProduct(ProductRequest request, String token);
    CommonResponse updateProduct(ProductRequest request, String token);
    CommonResponse deleteProduct(Long id, String token);
    ResultResponse sellerOwnProduct(String token);
}
