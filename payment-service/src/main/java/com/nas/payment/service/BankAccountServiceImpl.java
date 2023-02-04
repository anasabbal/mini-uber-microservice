package com.nas.payment.service;


import com.nas.core.util.JSONUtil;
import com.nas.payment.model.BankAccount;
import com.nas.payment.payload.RequestToBankAccount;
import com.nas.payment.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService{

    private final BankAccountRepository bankAccountRepository;
    private final RestTemplate restTemplate;

    @Override
    public BankAccount create(String  requestToBankAccount) {
        log.info(String.format("Received message -> %s", requestToBankAccount));
        final BankAccount bankAccount = BankAccount.create();
        log.info("[+] Bank Account with payload {} created successfully", JSONUtil.toJSON(bankAccount));
        bankAccount.setUserId(requestToBankAccount);
        bankAccountRepository.save(bankAccount);
        String url = "http://WALLET:2000/v1/wallet/" + bankAccount.getId();
        log.info("[+] URL : {}", url);
        restTemplate.getForObject(
                url,
                String.class,
                bankAccount.getId());
        log.info("[+] Bank Account created successfully with payload {}", JSONUtil.toJSON(bankAccount));
        return bankAccount;
    }

    @Override
    public Page<BankAccount> getAllAccount(Pageable pageable) {
        return bankAccountRepository.findByDeletedFalse(pageable);
    }
}
