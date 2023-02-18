package com.nas.customer.service.command;



public class RequestToBankAccount {

    private String customerId;
    
    public static RequestToBankAccount map(final String customerId){

        final RequestToBankAccount requestToBankAccount = new RequestToBankAccount();
        requestToBankAccount.customerId = customerId;

        return requestToBankAccount;
    }
}
