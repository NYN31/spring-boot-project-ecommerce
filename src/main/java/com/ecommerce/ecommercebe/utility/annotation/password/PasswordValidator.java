package com.ecommerce.ecommercebe.utility.annotation.password;


import com.ecommerce.ecommercebe.utility.Regex;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return value.matches(Regex.password);
    }
}
