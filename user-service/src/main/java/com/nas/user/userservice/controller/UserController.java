package com.nas.user.userservice.controller;


import com.nas.command.UserRegisterCommand;
import com.nas.user.userservice.service.UserService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                .POST("/users", this::createUser)
                .build();
    }

    private Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<UserRegisterCommand> userRegisterCommandMono = request.bodyToMono(UserRegisterCommand.class);
        return userRegisterCommandMono.flatMap(userService::create)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .onErrorResume(ValidationException.class, ex -> ServerResponse.badRequest().bodyValue(ex.getMessage()));
    }
}
