package com.nas.wallet.dto;

import com.nas.wallet.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;





@Getter
@Setter
public class TransactionWalletDto {
    protected String id;
    private Integer version;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    protected Boolean deleted;
    protected Boolean active;
    private Currency currency;

    private BigDecimal amount;
}
