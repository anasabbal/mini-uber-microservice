package com.nas.user.userservice.repository;


import com.nas.user.userservice.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
}
