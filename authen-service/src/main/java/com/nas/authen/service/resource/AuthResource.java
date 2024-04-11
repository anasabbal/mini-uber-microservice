package com.nas.authen.service.resource;


import com.nas.authen.service.command.RegisterCommand;
import com.nas.authen.service.model.User;
import com.nas.authen.service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.nas.core.constants.ResourcePath.AUTH;
import static com.nas.core.constants.ResourcePath.V1;

@RestController
@RequestMapping(V1 + AUTH)
@RequiredArgsConstructor
public class AuthResource {

    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> createPerson(@RequestBody RegisterCommand req) {
        return authService.register(req)
                .map(userId -> "User created successfully with ID: " + userId);
    }
}
