package com.nas.wallet.repository;

import com.nas.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findWalletByAccountId(String accountId);
}
