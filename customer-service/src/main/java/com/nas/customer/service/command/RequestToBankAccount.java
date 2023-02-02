package com.nas.customer.service.command;



public class RequestToBankAccount {
    private String id;
    public static RequestToBankAccount map(final String customerId){
        final RequestToBankAccount requestToBankAccount = new RequestToBankAccount();
        requestToBankAccount.id = customerId;

        return requestToBankAccount;
    }
}
