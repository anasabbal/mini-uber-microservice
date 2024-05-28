package com.nas.user.userservice.service;

import com.nas.command.UserRegisterCommand;
import com.nas.user.userservice.model.User;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> create(UserRegisterCommand userDto);
    Mono<User> retrieve(int userId);
}
