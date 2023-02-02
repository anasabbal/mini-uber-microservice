package com.nas.payment.controller;


import com.nas.payment.model.BankAccount;
import com.nas.payment.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nas.core.constants.ResourcePath.BANK_ACCOUNT;
import static com.nas.core.constants.ResourcePath.V1;

@RestController
@RequestMapping(V1 + BANK_ACCOUNT)
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

}
