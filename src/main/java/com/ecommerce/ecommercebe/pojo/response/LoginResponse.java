package com.ecommerce.ecommercebe.pojo.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponse extends CommonResponse{
    private String token;
}
