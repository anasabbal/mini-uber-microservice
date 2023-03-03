package com.nas.wallet.command;


import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class CreditCardCommand {
    @Column(name = "HOLD_NAME")
    private String holdName;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "EXPIRATION_DATE")
    private String expirationDate;

    @Column(name = "CVV")
    private String cvv;
}
