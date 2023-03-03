package com.nas.wallet.dto;


import com.nas.wallet.enums.PaymentStatus;
import com.nas.wallet.enums.PaymentType;
import com.nas.wallet.model.Client;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDto {
    protected String id;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private BigDecimal amount;
    private Client client;
    private CreditCardDto creditCard;
    private PaymentStatus paymentStatus;
    private String barCode;
    private PaymentType paymentType;
}
