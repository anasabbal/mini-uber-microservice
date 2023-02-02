package com.nas.payment.service;

import com.nas.payment.model.BankAccount;
import com.nas.payment.payload.RequestToBankAccount;

public interface BankAccountService {
    BankAccount create(RequestToBankAccount requestToBankAccount);
}
