package com.nas.payment.controller;


import com.nas.payment.model.BankAccount;
import com.nas.payment.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nas.core.constants.ResourcePath.*;

@RestController
@RequestMapping(V1 + PAYMENT)
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping("/{driverId}")
    public ResponseEntity<BankAccount> getOne(@PathVariable("driverId") final String driverId){
        return ResponseEntity.ok(bankAccountService.create(driverId));
    }
    @GetMapping(ACCOUNT_DETAILS + "/{userId}")
    public ResponseEntity<BankAccount> findById(@PathVariable("userId") final String userId){
        return ResponseEntity.ok(bankAccountService.findByUserId(userId));
    }
    @GetMapping
    public ResponseEntity<Page<BankAccount>> getAll(Pageable pageable){
        return ResponseEntity.ok(bankAccountService.getAllAccount(pageable));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteByUserId(@PathVariable("userId") final String userId){
        bankAccountService.deleteBankAccountByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
