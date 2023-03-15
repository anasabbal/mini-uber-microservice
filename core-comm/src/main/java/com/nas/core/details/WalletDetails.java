package com.nas.core.details;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;




@Getter
@Setter
public class WalletDetails {
    protected String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String updatedBy;
    protected Boolean deleted;
    protected Boolean active;
    private String accountId;
    private List<CreditCardDto> creditCards;
    private List<PaymentDto> payments;
}
