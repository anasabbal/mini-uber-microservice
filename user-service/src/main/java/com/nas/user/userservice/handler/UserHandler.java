package com.nas.user.userservice.handler;


import com.nas.command.UserRegisterCommand;
import com.nas.user.userservice.model.User;
import com.nas.user.userservice.service.UserService;
import com.nas.user.userservice.utils.ValidatorHandler;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;


@Slf4j
@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;
    private final ValidatorHandler validatorHandler;

    public Mono<ServerResponse> createUser(ServerRequest request) {

        return request.bodyToMono(UserRegisterCommand.class)
                .doOnNext(validatorHandler::validate)
                .flatMap(userService::create)
                .doOnSuccess(userSaved -> log.info("User saved with id: {} ", userSaved.getId()))
                .doOnError(e -> log.error("Error in saveUser method", e))
                .flatMap(user -> ServerResponse.created(getToUri(user)).bodyValue(user));
    }
    private URI getToUri(User userSaved) {
        return UriComponentsBuilder.fromPath(("/{id}"))
                .buildAndExpand(userSaved.getId()).toUri();
    }
}
