package com.ecommerce.ecommercebe.pojo.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtTokenResponse extends CommonResponse{
    private String tokenId;
}
