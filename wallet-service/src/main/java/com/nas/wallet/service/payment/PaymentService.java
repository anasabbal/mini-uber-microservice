package com.nas.wallet.service.payment;

import com.nas.wallet.command.PaymentCommand;
import com.nas.wallet.model.Payment;

public interface PaymentService {
    Payment create(final PaymentCommand paymentCommand);
}
