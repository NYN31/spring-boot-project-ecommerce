package com.ecommerce.ecommercebe.pojo.request;

import com.ecommerce.ecommercebe.utility.annotation.walletId.WalletId;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileEditRequest {
    @Size(min=4, max=32)
    private String name;

    @Email
    private String email;

    @Size(min=0, max=64)
    private String companyName ;

    @Size(min=0, max=64)
    private String address;

    @WalletId
    private String walletId;

    private String imageURL;
}
