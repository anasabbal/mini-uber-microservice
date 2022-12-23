package com.nas.auth.service;

import com.nas.auth.command.UserCommand;
import com.nas.auth.model.User;

public interface UserService {
    User create(final UserCommand userCommand);

}
