package com.nas.wallet.command;


import com.nas.wallet.enums.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletCommand {


    private String accountId;
    private Currency currency;
}
