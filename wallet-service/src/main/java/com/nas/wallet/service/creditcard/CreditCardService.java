package com.nas.wallet.service.creditcard;

import com.nas.wallet.command.CreditCardCommand;
import com.nas.wallet.model.CreditCard;

public interface CreditCardService {

    CreditCard create(final CreditCardCommand creditCardCommand);
}
