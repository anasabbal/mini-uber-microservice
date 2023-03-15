package com.nas.core.details;

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
    private CreditCardDto creditCard;
    private String paymentStatus;
    private String barCode;
    private String paymentType;
}