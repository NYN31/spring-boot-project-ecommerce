package com.ecommerce.ecommercebe.utility.annotation.walletId;

import com.ecommerce.ecommercebe.utility.annotation.password.PasswordValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = WalletValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface WalletId {
    String message() default "Invalid wallet number";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
