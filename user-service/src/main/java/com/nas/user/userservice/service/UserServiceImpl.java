package com.nas.user.userservice.service;

import com.nas.command.UserLoginCommand;
import com.nas.command.UserRegisterCommand;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.user.userservice.model.User;
import com.nas.user.userservice.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;




@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Mono<User> create(UserRegisterCommand request) {
        return Mono.just(request)
                .doOnNext(UserRegisterCommand::validate) // validate the request
                .flatMap(cmd -> {
                    // convert UserRegisterCommand to User
                    User user = User.create(cmd);
                    // encode password
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    // save the user and return the response
                    return userRepository.save(user);
                })
                .onErrorResume(ValidationException.class, ex -> {
                    // handle validation error and return a bad request response
                    return Mono.error(new RuntimeException("Validation error: " + ex.getMessage()));
                });
    }
    @Override
    public Mono<User> login(UserLoginCommand request) {
        return Mono.just(request)
                .doOnNext(UserLoginCommand::validate) // validate the request
                .flatMap(cmd -> {
                    return findByEmail(request.getEmail())
                            .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
                            .filter(foundUser -> passwordEncoder.matches(request.getPassword(), foundUser.getPassword()));
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Invalid credentials")));
    }
    private Mono<User> findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.EMAIL_ALREADY_EXIST.get())
        );
    }

    @Override
    public Mono<User> retrieve(int userId) {
        return null;
    }
}
