package com.nas.wallet.model;


import com.nas.wallet.command.CreditCardCommand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "BUYERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CreditCard extends BaseEntity {

    @Column(name = "HOLD_NAME")
    private String holdName;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "EXPIRATION_DATE")
    private String expirationDate;

    @Column(name = "CVV")
    private String cvv;

    @ManyToOne(optional = false)
    private Wallet wallet;

    public static CreditCard create(final CreditCardCommand command){
        final CreditCard creditCard = new CreditCard();

        creditCard.holdName = command.getHoldName();
        creditCard.number = command.getNumber();
        creditCard.expirationDate = command.getExpirationDate();
        creditCard.cvv = command.getCvv();

        return creditCard;
    }
    public void linkToWallet(Wallet wallet){
        this.wallet = wallet;
    }
}