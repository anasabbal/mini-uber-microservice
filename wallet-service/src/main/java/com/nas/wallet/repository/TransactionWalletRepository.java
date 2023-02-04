package com.nas.wallet.repository;

import com.nas.wallet.model.TransactionWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionWalletRepository extends JpaRepository<TransactionWallet, String> {
}
