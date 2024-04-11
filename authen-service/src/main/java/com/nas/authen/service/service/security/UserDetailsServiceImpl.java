package com.nas.authen.service.service.security;

import com.nas.authen.service.model.User;
import com.nas.authen.service.payload.UserDetailsImpl;
import com.nas.authen.service.repository.AuthRepository;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Mono<User> userMono =  authRepository.findByEmail(email);

        User user = userMono.block();
        if(user == null){
            throw new BusinessException(
                    ExceptionPayloadFactory.EMAIL_ALREADY_EXIST.get());
        }
        return UserDetailsImpl.build(user);
    }
}
