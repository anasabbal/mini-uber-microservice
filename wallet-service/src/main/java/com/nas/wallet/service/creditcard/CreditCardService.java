package com.nas.wallet.service.creditcard;

import com.nas.wallet.command.CreditCardCommand;
import com.nas.wallet.model.CreditCard;

public interface CreditCardService {

    CreditCard create(String walletId, final CreditCardCommand creditCardCommand);
    CreditCard findById(String creditCardId);
}
