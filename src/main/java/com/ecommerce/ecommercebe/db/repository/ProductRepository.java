package com.ecommerce.ecommercebe.db.repository;

import com.ecommerce.ecommercebe.db.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findById(Long aLong);
    Optional<ProductEntity> findByName(String name);
    Optional<ProductEntity> findByProductId(String productId);
    void deleteByProductId(String product);
}
