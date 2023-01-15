package com.nas.auth.service;

import com.nas.auth.command.UserCommand;
import com.nas.auth.model.Account;

public interface UserService {
    Account create(final UserCommand userCommand);

}
