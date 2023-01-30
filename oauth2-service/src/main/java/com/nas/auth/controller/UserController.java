package com.nas.auth.controller;


import com.nas.auth.command.UserCommand;
import com.nas.auth.model.Account;
import com.nas.auth.payload.JwtResponse;
import com.nas.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nas.core.constants.ResourcePath.*;

@RestController
@RequestMapping(V1 + AUTH)
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;


    @PostMapping(REGISTER)
    public ResponseEntity<Account> signUp(@RequestBody final UserCommand userCommand){
        return ResponseEntity.ok(userService.create(userCommand));
    }
    @PostMapping(LOGIN)
    public ResponseEntity<JwtResponse> login(@RequestBody final UserCommand userCommand){
        return ResponseEntity.ok(userService.login(userCommand));
    }
}
