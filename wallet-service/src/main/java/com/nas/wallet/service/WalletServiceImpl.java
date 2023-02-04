package com.nas.wallet.service;


import com.nas.wallet.model.Wallet;
import com.nas.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService{

    private final WalletRepository walletRepository;


    @Override
    public Wallet create(String accountId) {
        final Wallet wallet = Wallet.create();
        wallet.setAccountId(accountId);
        log.info("[+] Account with id {} set successfully", accountId);
        return walletRepository.save(wallet);
    }
}
