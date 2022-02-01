package com.ecommerce.ecommercebe.db.repository;

import com.ecommerce.ecommercebe.db.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
