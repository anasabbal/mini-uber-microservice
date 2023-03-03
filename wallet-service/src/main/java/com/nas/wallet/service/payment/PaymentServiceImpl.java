package com.nas.wallet.service.payment;




import com.nas.wallet.command.PaymentCommand;
import com.nas.wallet.model.Client;
import com.nas.wallet.model.CreditCard;
import com.nas.wallet.model.Payment;
import com.nas.wallet.model.Wallet;
import com.nas.wallet.repository.PaymentRepository;
import com.nas.wallet.service.creditcard.CreditCardService;
import com.nas.wallet.service.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{


    private final PaymentRepository paymentRepository;
    private final CreditCardService creditCardService;
    private final WalletService walletService;

    @Override
    public Payment create(PaymentCommand paymentCommand) {
        final CreditCard creditCard = creditCardService.findById(paymentCommand.getCreditCardId());
        final Wallet wallet = walletService.findByAccountId(paymentCommand.getClientId());
        final Client client = Client.create(wallet.getAccountId());

        log.info("[+] Begin creating payment with payload");
        final Payment payment = Payment.create(paymentCommand);

        payment.setCreditCard(creditCard);
        payment.setWallet(wallet);
        payment.setClient(client);
        return paymentRepository.save(payment);
    }
}
