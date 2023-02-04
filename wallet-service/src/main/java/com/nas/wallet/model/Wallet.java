package com.nas.wallet.model;


import com.nas.wallet.enums.Currency;
import lombok.*;

import javax.persistence.*;
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

    @OneToMany
    private List<TransactionWallet> transactionWallets;
}
