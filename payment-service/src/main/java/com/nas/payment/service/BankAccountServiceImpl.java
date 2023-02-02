package com.nas.payment.service;


import com.nas.core.util.JSONUtil;
import com.nas.payment.model.BankAccount;
import com.nas.payment.payload.RequestToBankAccount;
import com.nas.payment.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService{

    private final BankAccountRepository bankAccountRepository;

    @Override
    @RabbitListener(queues = "bank.customer")
    public BankAccount create(RequestToBankAccount requestToBankAccount) {
        log.info(String.format("Received message -> %s", requestToBankAccount.getId()));
        final BankAccount bankAccount = BankAccount.create();
        bankAccount.setUserId(requestToBankAccount.getId());
        log.info("[+] Bank Account created successfully with payload {}", JSONUtil.toJSON(bankAccount));
        return bankAccountRepository.save(bankAccount);
    }
}
