package com.nas.wallet.service.wallet;


import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.wallet.model.Wallet;
import com.nas.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService{

    private final WalletRepository walletRepository;
    private final RestTemplate restTemplate;


    @Override
    public Wallet create(String accountId) {
        log.info("[+] Begin Fetching account with id {} ", accountId);
        final Wallet wallet = Wallet.create(accountId);
        log.info("[+] Account with id {} set successfully", accountId);
        walletRepository.save(wallet);
        restTemplate.getForObject(
                "http://RATING:2018/v1/ratings/" + accountId, String.class, accountId
        );
        return wallet;
    }

    @Override
    public Wallet findById(String walletId){
        return walletRepository.findById(walletId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.WALLET_NOT_FOUND.get())
        );
    }

    @Override
    public void deleteWalletByAccountId(String accountId) {
        log.info("[+] Begin deleting wallet with account id {}", accountId);
        final Wallet wallet = findByAccountId(accountId);
        walletRepository.delete(wallet);
    }

    @Override
    public Wallet findByAccountId(String accountId) {
        log.info("[+] Begin fetching wallet by accountId {}", accountId);
        final Wallet wallet = walletRepository.findWalletByAccountId(accountId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.WALLER_FOR_ACCOUNT_NOT_FOUND.get())
        );
        log.info("[+] Wallet with id {} fetched successfully", wallet.getId());
        return wallet;
    }

    @Override
    public Page<Wallet> getAll(Pageable pageable) {
        return walletRepository.findAll(pageable);
    }
}
