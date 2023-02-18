package com.nas.wallet.service.wallet;


import com.nas.wallet.model.Wallet;
import com.nas.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService{

    private final WalletRepository walletRepository;


    @Override
    public Wallet create(String accountId) {
        log.info("[+] Begin Fetching account with id {} ", accountId);
        final Wallet wallet = Wallet.create(accountId);
        log.info("[+] Account with id {} set successfully", accountId);
        return walletRepository.save(wallet);
    }

    @Override
    public Page<Wallet> getAll(Pageable pageable) {
        return walletRepository.findAll(pageable);
    }
}
