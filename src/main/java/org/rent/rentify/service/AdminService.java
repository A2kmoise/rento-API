package org.rent.rentify.service;

import org.rent.rentify.model.Payment;
import org.rent.rentify.model.Property;
import org.rent.rentify.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    public Number totalUsers(){

    }

    public Number totalProperties(){

    }

    public Number pendingPayments(){

    }

    /*
    WHERE PAYMENTS ARE RECEIVED (STATUS=COMPLETE)
     */
    public Number revenueReceived(){}

    public List<User> getUsers(){}

    public List<Property> getPropertiesDetails(){}

    public List<Payment> getPaymentsDetails(){}

    public void deactivateAccount(){}

    public void validateAdmin(){}


}
