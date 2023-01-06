package com.nas.auth.controller;


import com.nas.auth.command.UserCommand;
import com.nas.auth.model.User;
import com.nas.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nas.core.constants.ResourcePath.USERS;
import static com.nas.core.constants.ResourcePath.V1;

@RestController
@RequestMapping(V1 + USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<User> signUp(@RequestBody final UserCommand userCommand){
        return ResponseEntity.ok(userService.create(userCommand));
    }
}
