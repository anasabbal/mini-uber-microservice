package com.nas.wallet.model;


import com.nas.wallet.enums.Currency;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Wallet extends BaseEntity{

    private String accountId;

    @OneToOne
    private Balance balance;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Currency.class)
    private List<Currency> currency;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "wallet")
    private List<TransactionWallet> transactionWallets;


    public static Wallet create(){
        final Wallet wallet = new Wallet();

        wallet.balance = Balance.create();
        wallet.transactionWallets = new ArrayList<>();

        return wallet;
    }
}
