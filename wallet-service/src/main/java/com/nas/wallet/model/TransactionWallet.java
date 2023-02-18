package com.nas.wallet.model;


import com.nas.wallet.enums.Currency;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionWallet extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @ManyToOne
    private Wallet wallet;

    public static TransactionWallet create(final Currency currency, final BigDecimal amount){
        final TransactionWallet transactionWallet = new TransactionWallet();

        transactionWallet.currency = currency;
        transactionWallet.amount = amount;

        return transactionWallet;
    }
}
