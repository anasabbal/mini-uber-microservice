package com.nas.wallet.model;

import com.nas.wallet.enums.Currency;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Balance{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    protected String id;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal amount;

    public static Balance create(final Currency currency){
        final Balance balance = new Balance();

        balance.currency = currency;
        balance.amount = BigDecimal.valueOf(13);

        return balance;
    }
}
