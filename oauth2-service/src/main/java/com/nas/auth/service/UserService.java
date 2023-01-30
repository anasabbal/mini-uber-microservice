package com.nas.auth.service;

import com.nas.auth.command.UserCommand;
import com.nas.auth.model.Account;
import com.nas.auth.payload.JwtResponse;

public interface UserService {
    Account create(final UserCommand userCommand);
    JwtResponse login(UserCommand userCommand);
}
