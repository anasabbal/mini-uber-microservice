package com.nas.wallet.service.transaction;

import java.math.BigDecimal;

import com.nas.wallet.enums.Currency;
import com.nas.wallet.model.TransactionWallet;

public interface TransactionService {
    TransactionWallet create(final Currency currency, final BigDecimal amount);
}
