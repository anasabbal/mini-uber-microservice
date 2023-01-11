package com.nas.auth.service;


import com.nas.auth.command.UserCommand;
import com.nas.auth.model.User;
import com.nas.auth.repository.UserRepository;
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

    private final UserRepository userRepository;


    @Override
    public User create(UserCommand userCommand) {
        userCommand.validate();

        if (userRepository.existsByEmail(userCommand.getEmail()))
            throw new BusinessException(ExceptionPayloadFactory.EMAIL_ALREADY_EXIST.get());
        if (userRepository.existsByUserName(userCommand.getUserName()))
            throw new BusinessException(ExceptionPayloadFactory.USER_NAME_ALREADY_EXIST.get());

        log.info("Begin creating user with payload {}", JSONUtil.toJSON(userCommand));
        final User user = userRepository.save(User.create(userCommand));
        log.info("User with id {}, saved successfully ", user.getId());

        return user;
    }
}
