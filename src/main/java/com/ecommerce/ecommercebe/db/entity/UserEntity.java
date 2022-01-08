package com.ecommerce.ecommercebe.db.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id ;

    @Column(name = "name")
    private String name ;

    @Column(name = "email")
    private String email ;

    @Column(name = "password")
    private String password ;

    @Column(name = "userType")
    private String userType ;

    @Column(name = "companyName")
    private String companyName ;

    @Column(name = "address")
    private String address ;

    @Column(name = "wallet_id", nullable = false, unique = true)
    @NotBlank
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