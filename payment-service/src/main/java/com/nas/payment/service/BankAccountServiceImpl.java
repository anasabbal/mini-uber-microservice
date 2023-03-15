package com.nas.payment.service;


import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import com.nas.payment.model.BankAccount;
import com.nas.payment.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        log.info("[+] URL :  http://WALLET:2000/v1/wallet/{}", bankAccount.getId());
        restTemplate.getForObject(
                "http://WALLET:2000/v1/wallet/" + bankAccount.getId(),
                String.class,
                bankAccount.getId());
        log.info("[+] Bank Account created successfully with payload {}", JSONUtil.toJSON(bankAccount));
        return bankAccount;
    }
    @Override
    public BankAccount findByUserId(String userId){
        log.info("[+] Begin fetching Bank account with userId {}", userId);
        final BankAccount bankAccount = bankAccountRepository.findBankAccountByUserId(userId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.BANK_ACCOUNT_NOT_FOUND.get())
        );
        log.info("[+] Bank Account with id {} fetched successfully", bankAccount.getId());
        return bankAccount;
    }
    @Override
    public void deleteBankAccountByUserId(String userId) {
        final BankAccount bankAccount = findByUserId(userId);
        bankAccountRepository.delete(bankAccount);
        restTemplate.delete("http://WALLET:2000/v1/wallet/" + bankAccount.getId(), bankAccount.getId());
    }

    @Override
    public Page<BankAccount> getAllAccount(Pageable pageable) {
        return bankAccountRepository.findByDeletedFalse(pageable);
    }
}
