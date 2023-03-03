package com.nas.wallet.service.payment;



import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.wallet.command.PaymentCommand;
import com.nas.wallet.model.Client;
import com.nas.wallet.model.CreditCard;
import com.nas.wallet.model.Payment;
import com.nas.wallet.model.Wallet;
import com.nas.wallet.repository.CreditCardRepository;
import com.nas.wallet.repository.PaymentRepository;
import com.nas.wallet.repository.WalletRepository;
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
    private final CreditCardRepository creditCardRepository;
    private final WalletRepository walletRepository;

    @Override
    public Payment create(PaymentCommand paymentCommand) {
        log.info("[+] Begin creating payment with payload");
        final Payment payment = Payment.create(paymentCommand);

        log.info("[+] Begin fetching credit card with id {}", paymentCommand.getCreditCardId());
        final CreditCard creditCard = creditCardRepository.findById(paymentCommand.getCreditCardId()).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.CREDIT_CARD_NOT_FOUND.get())
        );
        log.info("[+] Credit Card with id {} fetched successfully", creditCard.getId());
        log.info("[+]------------------------------------------[+]");

        log.info("[+] Begin fetching wallet with account id {}", paymentCommand.getClientId());
        final Wallet wallet = walletRepository.findWalletByAccountId(paymentCommand.getClientId()).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.WALLER_FOR_ACCOUNT_NOT_FOUND.get())
        );
        log.info("[+] Wallet with id {} fetched successfully", wallet.getId());
        final Client client = Client.create(wallet.getAccountId());

        payment.setCreditCard(creditCard);
        payment.setWallet(wallet);
        payment.setClient(client);
        return paymentRepository.save(payment);
    }
}
