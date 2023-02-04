package com.nas.wallet.dto;


import com.nas.wallet.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BalanceDto {
    protected String id;
    private Currency currency;
    private BigDecimal amount;
}
