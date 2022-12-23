package com.nas.auth.repository;

import com.nas.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);
}
