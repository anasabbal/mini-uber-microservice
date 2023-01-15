package com.nas.auth.service;


import com.nas.auth.command.UserCommand;
import com.nas.auth.model.Account;
import com.nas.auth.repository.AuthRepository;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService{

    private final AuthRepository authRepository;


    @Override
    public Account create(UserCommand userCommand) {
        userCommand.validate();

        if (authRepository.existsByEmail(userCommand.getEmail()))
            throw new BusinessException(ExceptionPayloadFactory.EMAIL_ALREADY_EXIST.get());
        if (authRepository.existsByUserName(userCommand.getUserName()))
            throw new BusinessException(ExceptionPayloadFactory.USER_NAME_ALREADY_EXIST.get());

        log.info("Begin creating user with payload {}", JSONUtil.toJSON(userCommand));
        final Account account = authRepository.save(Account.create(userCommand));
        log.info("User with id {}, saved successfully ", account.getId());

        return account;
    }
}
