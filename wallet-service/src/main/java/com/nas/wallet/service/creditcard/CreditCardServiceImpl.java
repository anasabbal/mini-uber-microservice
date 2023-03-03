package com.nas.wallet.service.creditcard;



import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import com.nas.wallet.command.CreditCardCommand;
import com.nas.wallet.model.CreditCard;
import com.nas.wallet.model.Wallet;
import com.nas.wallet.repository.CreditCardRepository;
import com.nas.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService{

    private final CreditCardRepository creditCardRepository;
    private final WalletRepository walletRepository;


    @Override
    public CreditCard create(String walletId, CreditCardCommand creditCardCommand) {
        log.info("[+] Begin fetching wallet with account id {}", walletId);
        final Wallet wallet = walletRepository.findWalletByAccountId(walletId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.WALLER_FOR_ACCOUNT_NOT_FOUND.get())
        );
        log.info("[+] Wallet with id {} fetched successfully", wallet.getId());

        log.info("[+] Begin creating Credit Card with payload {}", JSONUtil.toJSON(creditCardCommand));
        final CreditCard creditCard = CreditCard.create(creditCardCommand);
        creditCard.linkToWallet(wallet);
        wallet.addCreditCard(creditCard);
        log.info("[+] Credit Card with id {} created successfully", creditCard.getId());
        return creditCardRepository.save(creditCard);
    }
}
