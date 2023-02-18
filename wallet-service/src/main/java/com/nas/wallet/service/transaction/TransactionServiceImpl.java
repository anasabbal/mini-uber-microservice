package com.nas.wallet.service.transaction;


import com.nas.wallet.enums.Currency;
import com.nas.wallet.model.TransactionWallet;
import com.nas.wallet.repository.TransactionWalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionWalletRepository transactionWalletRepository;


    @Override
    public TransactionWallet create(final Currency currency, final BigDecimal amount){
        return null;
    }
}
