package com.ecommerce.ecommercebe.service;

import com.ecommerce.ecommercebe.pojo.request.UserProfileEditRequest;
import com.ecommerce.ecommercebe.pojo.response.CommonResponse;
import com.ecommerce.ecommercebe.pojo.response.ProductListResponse;
import com.ecommerce.ecommercebe.pojo.response.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface CommonUserFeaturesService {
    CommonResponse editUserProfile(UserProfileEditRequest request, String token);
    ProductListResponse getAllProduct(String token);
    ProductResponse getProductById(Long id);
}
