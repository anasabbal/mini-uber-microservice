package com.nas.authen.service.repository;

import com.nas.authen.service.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface AuthRepository extends ReactiveCrudRepository<User, String> {
    Mono<User> findByEmail(String email);
}
