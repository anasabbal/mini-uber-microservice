package com.nas.wallet.command;


import com.nas.wallet.enums.Currency;
import lombok.Getter;

import java.util.List;

@Getter
public class WalletCommand {

    private String accountId;
    private Currency currency;
    private List<CreditCardCommand> creditCards;
}
