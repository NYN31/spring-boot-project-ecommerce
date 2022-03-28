package com.ecommerce.ecommercebe.utility.utilClasses;

import com.ecommerce.ecommercebe.db.repository.ProductRepository;
import com.ecommerce.ecommercebe.pojo.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoolingPeriod {

    @Autowired
    private ProductRepository productRepository;

    private final int ADD_PRODUCT = 1;
    private final int UPDATE_PROFILE = 30;

    public void checkCollingPeriodForProductUpdate(ProductRequest request, Long sellerId) {
        // implemented later
    }
}
