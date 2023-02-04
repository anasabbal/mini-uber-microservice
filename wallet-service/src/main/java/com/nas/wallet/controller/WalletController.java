package com.nas.wallet.controller;


import com.nas.wallet.dto.WalletDto;
import com.nas.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nas.core.constants.ResourcePath.V1;
import static com.nas.core.constants.ResourcePath.WALLET;

@RestController
@RequestMapping(V1 + WALLET)
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/{accountId}")
    public ResponseEntity<WalletDto> getAndCreate(@PathVariable("accountId") final String accountId){
        return null;
    }
}
