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
    public Mono<User> create(UserRegisterCommand request) {
        return Mono.just(request)
                .doOnNext(UserRegisterCommand::validate) // validate the request
                .flatMap(cmd -> {
                    // convert UserRegisterCommand to User
                    User user = User.create(cmd);
                    // save the user and return the response
                    return userRepository.save(user);
                })
                .onErrorResume(ValidationException.class, ex -> {
                    // handle validation error and return a bad request response
                    return Mono.error(new RuntimeException("Validation error: " + ex.getMessage()));
                });
    }

    @Override
    public Mono<User> retrieve(int userId) {
        return null;
    }
}
