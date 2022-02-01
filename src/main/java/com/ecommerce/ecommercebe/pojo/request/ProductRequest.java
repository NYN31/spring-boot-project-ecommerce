package com.ecommerce.ecommercebe.pojo.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRequest {
    @Size(min=4, max=64)
    private String name;

    @Size(min=4, max=32)
    private String tag;

    @Min(0)
    private Long price ;

    @Min(0)
    private Long quantity ;

    @Min(0)
    private Double rating ;
}
