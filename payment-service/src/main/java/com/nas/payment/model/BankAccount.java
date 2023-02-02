package com.nas.payment.model;


import com.nas.payment.enums.AccountStatus;
import com.nas.payment.enums.AccountType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BankAccount extends BaseEntity{

    @Column(name = "USER_ID")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE")
    private AccountType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_STATUS")
    private AccountStatus status;

    public static BankAccount create(){
        final BankAccount bankAccount = new BankAccount();

        bankAccount.type = AccountType.SAVINGS_ACCOUNT;
        bankAccount.status = AccountStatus.ACTIVE;

        return bankAccount;
    }

}
