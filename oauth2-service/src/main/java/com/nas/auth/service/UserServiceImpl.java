package com.nas.auth.service;


import com.nas.auth.command.UserCommand;
import com.nas.auth.model.Account;
import com.nas.auth.payload.JwtResponse;
import com.nas.auth.payload.UserDetailsImpl;
import com.nas.auth.repository.AuthRepository;
import com.nas.auth.security.UserDetailsServiceImpl;
import com.nas.auth.utils.TokenHandler;
import com.nas.auth.utils.TokenHandler2;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final AuthRepository authRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final TokenHandler tokenHandler;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Account create(UserCommand userCommand) {
        userCommand.validate();

        if (authRepository.existsByEmail(userCommand.getEmail()))
            throw new BusinessException(ExceptionPayloadFactory.EMAIL_ALREADY_EXIST.get());
        if (authRepository.existsByUserName(userCommand.getUserName()))
            throw new BusinessException(ExceptionPayloadFactory.USER_NAME_ALREADY_EXIST.get());

        log.info("Begin creating user with payload {}", JSONUtil.toJSON(userCommand));
        final Account account = Account.create(userCommand);
        account.setPassword(passwordEncoder.encode(userCommand.getPassword()));
        log.info("User with id {}, saved successfully ", account.getId());

        return authRepository.save(account);
    }
    @Override
    public JwtResponse login(UserCommand userCommand) {
        // If the authentication process is successful,
        // we can get Users information such as email, password, authorities from an Authentication
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userCommand.getEmail(), userCommand.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl base = (UserDetailsImpl) authentication.getPrincipal();

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userCommand.getEmail());
        List<String> roles = base.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        final String token = tokenHandler.generateToken((Authentication) userDetails);
        log.info("token : {}", token);
        log.info("authority {}", userDetails.getAuthorities());
        return new JwtResponse(base.getUserId(), token, base.getUsername(), roles);
    }
}
