package com.nas.auth.service;

import com.nas.auth.command.UserCommand;
import com.nas.auth.model.Customer;
import com.nas.auth.model.User;
import com.nas.auth.repository.UserRepository;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public record UserDetailsServiceImpl(UserRepository userRepository, RestTemplate restTemplate) implements UserDetailsService, UserService{

    @Override
    public User create(final UserCommand userCommand){
        final User user = userRepository.saveAndFlush(User.create(userCommand));
        final Customer customer = restTemplate.getForObject("localhost:8080/v1/customers", Customer.class, user.getCustomerId());
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUserName(username).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.USER_NAME_NOT_FOUND.get())
                );
        return null;
    }
}
