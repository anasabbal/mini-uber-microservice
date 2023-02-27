package com.nas.wallet.service.wallet;


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
    public Page<Wallet> getAll(Pageable pageable) {
        return walletRepository.findAll(pageable);
    }
}
