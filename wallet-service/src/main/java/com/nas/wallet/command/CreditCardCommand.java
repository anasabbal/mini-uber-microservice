package com.nas.wallet.command;


import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class CreditCardCommand {
    private String holdName;
    private String number;
    private String expirationDate;
    private String cvv;
}
