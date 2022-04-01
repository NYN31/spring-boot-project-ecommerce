package com.ecommerce.ecommercebe.pojo.response;

import com.ecommerce.ecommercebe.db.entity.ProductEntity;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponse extends CommonResponse{
    private ProductEntity product;
}
