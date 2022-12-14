package com.nisum.service.presentation.controller;

import com.nisum.service.presentation.presenter.UserPresenter;
import com.nisum.service.service.UserService;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Generated
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userId")
    public UserPresenter getUserById(@RequestParam("userId") UUID userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/saveUpdateUser")
    public UserPresenter saveUpdateUser(@RequestBody UserPresenter userPresenter) {
        return userService.saveUpdateUser(userPresenter);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUserById(@RequestParam("userId") UUID userId) {
        userService.deleteUserById(userId);
    }


}
