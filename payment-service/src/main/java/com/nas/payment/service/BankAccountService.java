package com.nas.payment.service;

import com.nas.payment.model.BankAccount;
import com.nas.payment.payload.RequestToBankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankAccountService {
    BankAccount create(String requestToBankAccount);
    Page<BankAccount> getAllAccount(Pageable pageable);
}
