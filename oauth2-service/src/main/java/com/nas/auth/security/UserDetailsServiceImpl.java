package com.nas.auth.security;

import com.nas.auth.model.Account;
import com.nas.auth.payload.UserDetailsImpl;
import com.nas.auth.repository.AuthRepository;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * fetching user details from the database using the email.
 * The Spring Security Authentication Manager calls this method for getting the user
 * details from the database when authenticating the user details provided by the user.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Account account = accountRepository.findAccountByCustomerEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.USER_NAME_NOT_FOUND.get())
        );
        return UserDetailsImpl.build(account);
    }
}
