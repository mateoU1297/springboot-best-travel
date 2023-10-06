package com.udemy.best_travel.infraestructure.helpers;

import com.udemy.best_travel.util.exceptions.ForbiddenCustomerException;
import org.springframework.stereotype.Component;

@Component
public class BlackListHelper {

    public void isInBlackListCustomer(String customerId) {
        if(customerId.equals("GOTW771012HMRGR087")) {
            throw new ForbiddenCustomerException();
        }
    }
}
