package com.ecommerce.ecommercebe.db.entity;

import com.ecommerce.ecommercebe.utility.enums.TokenStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token_details")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wallet_Id")
    @NotBlank
    @NotNull
    private String walletId;

    @Column(name = "email")
    @NotBlank
    @NotNull
    private String email;

    @Column(name = "token_id")
    @NotBlank
    @NotNull
    private String tokenId;

    @Column(name = "token")
    @NotBlank
    @NotNull
    private String token;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, updatable = false)
    @UpdateTimestamp
    private Date expiredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TokenStatus status = TokenStatus.ACTIVE;
}
