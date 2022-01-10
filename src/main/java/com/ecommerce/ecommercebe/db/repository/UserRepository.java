package com.ecommerce.ecommercebe.db.repository;

import com.ecommerce.ecommercebe.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
    UserEntity findByWalletId(String walletId);
}
