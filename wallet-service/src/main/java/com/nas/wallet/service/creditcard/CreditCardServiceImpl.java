package com.nas.wallet.service.creditcard;



import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import com.nas.wallet.command.CreditCardCommand;
import com.nas.wallet.model.CreditCard;
import com.nas.wallet.model.Wallet;
import com.nas.wallet.repository.CreditCardRepository;
import com.nas.wallet.service.wallet.WalletService;
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
    private final WalletService walletService;


    @Override
    public CreditCard create(String walletId, CreditCardCommand creditCardCommand) {
        final Wallet wallet = walletService.findById(walletId);

        log.info("[+] Begin creating Credit Card with payload {}", JSONUtil.toJSON(creditCardCommand));
        final CreditCard creditCard = CreditCard.create(creditCardCommand);

        creditCard.linkToWallet(wallet);
        wallet.addCreditCard(creditCard);

        return creditCardRepository.save(creditCard);
    }
    @Override
    public CreditCard findById(String creditCardId){
        return creditCardRepository.findById(creditCardId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.CREDIT_CARD_NOT_FOUND.get())
        );
    }
}
