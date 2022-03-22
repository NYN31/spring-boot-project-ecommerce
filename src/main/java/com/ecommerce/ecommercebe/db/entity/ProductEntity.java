package com.ecommerce.ecommercebe.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id ;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "tag")
    @NotNull
    private String tag;

    @Column(name = "price")
    @NotNull
    @Min(0)
    private Long price ;

    @Column(name = "quantity")
    @Min(0)
    @NotNull
    private Long quantity ;

    @Column(name = "rating")
    @Min(0)
    @NotNull
    private Double rating = 0.0;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @JsonIgnore
    @ToString.Exclude
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    @JsonIgnore
    @ToString.Exclude
    private Date updateAt;
}

