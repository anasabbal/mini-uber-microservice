package com.nas.wallet.service.creditcard;



import com.nas.core.util.JSONUtil;
import com.nas.wallet.command.CreditCardCommand;
import com.nas.wallet.model.CreditCard;
import com.nas.wallet.repository.CreditCardRepository;
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


    @Override
    public CreditCard create(CreditCardCommand creditCardCommand) {
        log.info("[+] Begin creating Credit Card with payload {}", JSONUtil.toJSON(creditCardCommand));
        final CreditCard creditCard = CreditCard.create(creditCardCommand);
        log.info("[+] Credit Card with id {} created successfully", creditCard.getId());
        return creditCardRepository.save(creditCard);
    }
}
