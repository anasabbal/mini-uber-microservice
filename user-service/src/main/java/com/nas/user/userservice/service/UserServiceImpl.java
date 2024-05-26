package com.nas.user.userservice.service;

import com.nas.command.UserRegisterCommand;
import com.nas.user.userservice.model.User;
import com.nas.user.userservice.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;




@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;

    @Override
    public Mono<ServerResponse> create(UserRegisterCommand request) {
        return Mono.just(request)
                .doOnNext(UserRegisterCommand::validate) // validate the request
                .flatMap(req -> {
                    // convert UserRegisterCommand to User
                    final User user = User.create(request);
                    // save the user and return the response
                    return ServerResponse.ok().body(userRepository.save(user), User.class);
                })
                .onErrorResume(ValidationException.class, ex -> {
                    // handle validation error and return a bad request response
                    return ServerResponse.badRequest().syncBody(ex.getMessage());
                });
    }
}
