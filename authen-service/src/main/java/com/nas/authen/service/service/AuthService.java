package com.nas.authen.service.service;

import com.nas.authen.service.command.RegisterCommand;
import com.nas.authen.service.model.User;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<User> register(RegisterCommand request);
}
