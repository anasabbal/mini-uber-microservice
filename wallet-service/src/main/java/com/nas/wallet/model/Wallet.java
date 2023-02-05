package com.nas.wallet.model;


import com.nas.wallet.enums.Currency;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Wallet extends BaseEntity{





    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Balance balance;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Currency.class)
    private List<Currency> currency;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "wallet")
    private List<TransactionWallet> transactionWallets;

    @Column(name = "ACCOUNT_ID")
    private String accountId;


    public static Wallet create(String accountId){
        final Wallet wallet = new Wallet();

        wallet.balance = Balance.create();
        wallet.transactionWallets = new ArrayList<>();
        wallet.accountId = accountId;

        return wallet;
    }
}
