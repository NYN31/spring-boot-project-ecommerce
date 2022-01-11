package com.ecommerce.ecommercebe.pojo.request;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtTokenRequest {
    @Email
    private String email;
}
