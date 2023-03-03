package com.nas.wallet.command;


import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentCommand {
    private BigDecimal amount;
    private String clientId;
    private String creditCardId;
    private String paymentStatus;
}
