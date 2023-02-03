package com.nas.payment.repository;

import com.nas.payment.model.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    Page<BankAccount> findByDeletedFalse(Pageable pageable);
}
