package com.ecommerce.ecommercebe.utility.annotation.walletId;

import com.ecommerce.ecommercebe.utility.Regex;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WalletValidator implements ConstraintValidator<WalletId, String> {
    @Override
    public boolean isValid(String walletNo, ConstraintValidatorContext context){
        return walletNo.matches(Regex.walletId);
    }
}
