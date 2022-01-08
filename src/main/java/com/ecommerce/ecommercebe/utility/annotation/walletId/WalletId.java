package com.ecommerce.ecommercebe.utility.annotation.walletId;

public @interface WalletId {
    String message() default "Invalid wallet number";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
