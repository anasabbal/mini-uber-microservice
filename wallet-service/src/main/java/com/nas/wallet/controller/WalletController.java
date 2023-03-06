package com.nas.wallet.controller;


import com.nas.wallet.dto.WalletDto;
import com.nas.wallet.dto.mapper.WalletMapper;
import com.nas.wallet.service.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.nas.core.constants.ResourcePath.*;

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
    @DeleteMapping(PAYMENT + "/{accountId}")
    public ResponseEntity<Void> deleteByAccountId(@PathVariable("accountId") final String accountId){
        walletService.deleteWalletByAccountId(accountId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(PAYMENT + "/{accountId}")
    public ResponseEntity<WalletDto> getWalletByAccountId(@PathVariable("accountId") final String accountId){
        return ResponseEntity.ok(walletMapper.toDto(walletService.findByAccountId(accountId)));
    }
}
