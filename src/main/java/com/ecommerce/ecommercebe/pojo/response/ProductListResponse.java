package com.ecommerce.ecommercebe.pojo.response;

import com.ecommerce.ecommercebe.db.entity.ProductEntity;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductListResponse extends CommonResponse{
    private List<ProductEntity> products;
}
