package com.nas.user.userservice.service;

import com.nas.command.UserRegisterCommand;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface UserService {
    public Mono<ServerResponse> create(UserRegisterCommand request);
}
