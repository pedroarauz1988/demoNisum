package com.nisum.service.service;


import com.nisum.service.presentation.presenter.UserPresenter;

import java.util.UUID;

public interface UserService {

    UserPresenter getUserById(UUID userId);

    UserPresenter saveUpdateUser(UserPresenter userPresenter);

    void deleteUserById(UUID userId);
}
