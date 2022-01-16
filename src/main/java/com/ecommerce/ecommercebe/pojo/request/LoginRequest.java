package com.ecommerce.ecommercebe.pojo.request;

import com.ecommerce.ecommercebe.utility.annotation.password.Password;
import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequest {
    @Email
    private String email;

    private String password;
}
