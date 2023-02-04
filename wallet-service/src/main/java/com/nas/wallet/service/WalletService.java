package com.nas.wallet.service;

import com.nas.wallet.model.Wallet;

public interface WalletService {
    Wallet create(final String accountId);
}
