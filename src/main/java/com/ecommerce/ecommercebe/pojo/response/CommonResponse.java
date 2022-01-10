package com.ecommerce.ecommercebe.pojo.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommonResponse {
    private Integer code;
    private String message;
}
