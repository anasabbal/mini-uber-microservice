package com.nas.wallet.controller;


import com.nas.wallet.dto.WalletDto;
import com.nas.wallet.dto.mapper.WalletMapper;
import com.nas.wallet.service.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final WalletMapper walletMapper;

    @GetMapping("/{accountId}")
    public ResponseEntity<WalletDto> getAndCreate(@PathVariable("accountId") final String accountId){
        return ResponseEntity.ok(walletMapper.toDto(walletService.create(accountId)));
    }
    @GetMapping
    public ResponseEntity<Page<WalletDto>> getWallets(Pageable pageable){
        return ResponseEntity.ok(walletService.getAll(pageable).map(walletMapper::toDto));
    }
}
