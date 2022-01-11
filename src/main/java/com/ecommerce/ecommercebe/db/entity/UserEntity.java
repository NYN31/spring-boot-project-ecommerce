package com.ecommerce.ecommercebe.db.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @Column(name = "name")
    @NotBlank
    @NotNull
    private String name ;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @NotNull
    private String email ;

    @Column(name = "password", nullable = false, unique = true)
    @NotBlank
    @NotNull
    private String password ;

    @Column(name = "userType")
    @NotBlank
    @NotNull
    private String userType ;

    @Column(name = "companyName")
    @NotBlank
    @NotNull
    private String companyName ;

    @Column(name = "address")
    @NotBlank
    @NotNull
    private String address ;

    @Column(name = "wallet_id", nullable = false, unique = true)
    @NotBlank
    @NotNull
    private String walletId;

    @Column(name = "imageUrl")
    private String imageUrl ;

    @Column(name = "balance")
    private Double balance ;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}