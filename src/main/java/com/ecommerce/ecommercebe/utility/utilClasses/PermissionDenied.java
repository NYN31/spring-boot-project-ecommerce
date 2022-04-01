package com.ecommerce.ecommercebe.utility.utilClasses;

import com.ecommerce.ecommercebe.exception.PermissionException;
import org.springframework.stereotype.Component;

@Component
public class PermissionDenied {
    public final String USER_TYPE1 = "buyer";
    public final String USER_TYPE2 = "seller";

    public void ForBuyer(String userType){
        if(userType.equals(USER_TYPE1)) {
            throw new PermissionException("You don't have the permission as a buyer");
        }
    }

    public void ForSeller(String userType){
        if(userType.equals(USER_TYPE2)){
            throw new PermissionException("You don't have the permission as a seller");
        }
    }
}
