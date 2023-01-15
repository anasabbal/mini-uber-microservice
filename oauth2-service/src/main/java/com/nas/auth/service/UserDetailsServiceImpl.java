package com.nas.auth.service;

import com.nas.auth.command.UserCommand;
import com.nas.auth.model.Customer;
import com.nas.auth.model.Account;
import com.nas.auth.repository.AuthRepository;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public record UserDetailsServiceImpl(AuthRepository authRepository, RestTemplate restTemplate) implements UserDetailsService, UserService{

    @Override
    public Account create(final UserCommand userCommand){
        final Account account = authRepository.saveAndFlush(Account.create(userCommand));
        final Customer customer = restTemplate.getForObject("localhost:8080/v1/customers", Customer.class, account.getCustomerId());
        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Account account = authRepository.findByUserName(username).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.USER_NAME_NOT_FOUND.get())
                );
        return null;
    }
}
