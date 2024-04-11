package com.nas.authen.service.service;


import com.nas.authen.service.command.RegisterCommand;
import com.nas.authen.service.model.User;
import com.nas.authen.service.repository.AuthRepository;
import com.nas.core.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{


    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<User> register(RegisterCommand request) {
        log.info("Begin creating user with payload {}", JSONUtil.toJSON(request));
        final User user = User.create(request);
        log.info("User with id {} created successfully", user.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Mono<User> customerMono = authRepository.save(user).log();
        log.info("User with saved successfully !");
        return customerMono;
    }
}
