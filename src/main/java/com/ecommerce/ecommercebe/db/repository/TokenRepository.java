package com.ecommerce.ecommercebe.db.repository;

import com.ecommerce.ecommercebe.db.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByTokenIdAndWalletId(String tokenId, String walletId);
    TokenEntity findByToken(String token);
    TokenEntity findByTokenId(String tokenId);
}
