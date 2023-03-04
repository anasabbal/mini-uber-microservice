package com.nas.wallet.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "WALLETS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Wallet extends BaseEntity {


    @Column(name = "ACCOUNT_ID")
    private String accountId;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CreditCard> creditCards = new LinkedList<>();

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Payment> payments = new LinkedList<>();


    public static Wallet create(final String accountId){
        final Wallet wallet = new Wallet();

        wallet.accountId = accountId;

        return wallet;
    }
    public void addPayment(Payment payment){
        this.payments.add(payment);
    }
    public void addCreditCard(CreditCard creditCard){
        this.creditCards.add(creditCard);
    }
}
