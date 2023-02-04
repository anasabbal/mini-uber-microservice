package com.nas.wallet.dto;


import com.nas.wallet.enums.Currency;
import com.nas.wallet.model.Balance;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class WalletDto {
    protected String id;
    private Integer version;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    protected Boolean deleted;
    protected Boolean active;
    private String accountId;
    private BalanceDto balance;
    private List<Currency> currency;
    private List<TransactionWalletDto> transactionWallets;
}
