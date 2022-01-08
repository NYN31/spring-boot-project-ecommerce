package com.ecommerce.ecommercebe.pojo.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String company ;
    private String address;
    private String imageURL;
}