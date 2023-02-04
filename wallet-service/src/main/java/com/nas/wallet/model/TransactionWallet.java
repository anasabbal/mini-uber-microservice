package com.nas.wallet.model;


import com.nas.wallet.enums.Currency;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionWallet extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal amount;

    @ManyToOne
    private Wallet wallet;
}
