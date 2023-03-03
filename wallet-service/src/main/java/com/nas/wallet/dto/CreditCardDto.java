package com.nas.wallet.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreditCardDto {
    protected String id;
    private String createdBy;
    private String holdName;
    private String number;
    private String expirationDate;
    private String cvv;
}
