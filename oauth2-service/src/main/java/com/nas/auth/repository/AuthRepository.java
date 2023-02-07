package com.nas.auth.repository;

import com.nas.auth.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthRepository extends JpaRepository<Account, String> {
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
    Optional<Account> findByUserName(String userName);
    Optional<Account> findAccountByEmail(String email);
}
