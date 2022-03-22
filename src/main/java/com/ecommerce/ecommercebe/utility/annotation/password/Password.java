package com.ecommerce.ecommercebe.utility.annotation.password;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: regex not working fine for password
@Constraint(validatedBy = PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Password {
    String message() default "Password must be at least 6 digit long and" +
            "contains only letters and digits";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
