package com.ecommerce.ecommercebe.pojo.request;

import com.ecommerce.ecommercebe.utility.annotation.password.Password;
import com.ecommerce.ecommercebe.utility.annotation.walletId.WalletId;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequest {
    @Size(min=4, max=32)
    private String name;

    @Email
    private String email;

    @Password
    private String password;

    private String companyName ;

    @Size(min=0, max=64)
    private String address;

    @WalletId
    private String walletId;

    private String imageURL;

    @Min(0)
    private Double balance;
}