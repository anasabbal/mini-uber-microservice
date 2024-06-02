package com.nas.user.userservice.service;

import com.nas.command.UserLoginCommand;
import com.nas.command.UserRegisterCommand;
import com.nas.user.userservice.model.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> login(UserLoginCommand request);
    Mono<User> create(UserRegisterCommand userDto);
    Mono<User> retrieve(int userId);
}
