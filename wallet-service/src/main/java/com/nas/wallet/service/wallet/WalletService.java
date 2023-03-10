package com.nas.wallet.service.wallet;


import com.nas.wallet.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WalletService {
    Wallet create(final String accountId);
    Page<Wallet> getAll(Pageable pageable);
    Wallet findByAccountId(String accountId);
    void deleteWalletByAccountId(String accountId);
    Wallet findById(String walletId);
}
