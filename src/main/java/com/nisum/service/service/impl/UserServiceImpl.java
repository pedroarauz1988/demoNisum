package com.nisum.service.service.impl;


import com.nisum.service.entity.Phone;
import com.nisum.service.entity.User;
import com.nisum.service.enums.DefaultConfigurationNames;
import com.nisum.service.exception.ValidationException;
import com.nisum.service.presentation.presenter.UserPresenter;
import com.nisum.service.repository.PhoneRepository;
import com.nisum.service.repository.UserRepository;
import com.nisum.service.service.DefaultConfigurationService;
import com.nisum.service.service.UserService;
import com.nisum.service.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DefaultConfigurationService defaultConfigurationService;

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public UserPresenter getUserById(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return toPresenter(user.get());
        } else {
            throw new ValidationException("User account not found");
        }
    }


    @Override
    @Transactional
    public UserPresenter saveUpdateUser(UserPresenter userPresenter) {
        User user;
        Boolean isNewUser = false;
        Set<Phone> phones = new HashSet<>();
        validateUserPresenter(userPresenter);

        if (userPresenter.getId() == null) {
            user = new User();
            isNewUser = true;
        } else {
            Optional<User> userOptional = userRepository.findById(userPresenter.getId());
            if (userOptional.isPresent()) {
                user = userOptional.get();
                if (user.getPhones() != null && !user.getPhones().isEmpty()) {
                    user.getPhones().forEach(phone -> {
                        phoneRepository.delete(phone);
                    });
                }
            } else {
                user = new User();
                isNewUser = false;
            }
        }
        if (isNewUser) {
            String email = userPresenter.getEmail();
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                throw new ValidationException("El email ya se encuentra registrado para el usuario: " + optionalUser.get().getName());
            }
        }
        if (userPresenter.getPhones() != null && !userPresenter.getPhones().isEmpty()) {
            userPresenter.getPhones().forEach(phonePresenter -> {
                phones.add(phoneRepository.save(Phone.builder()
                        .number(phonePresenter.getNumber())
                        .cityCode(phonePresenter.getCityCode())
                        .countryCode(phonePresenter.getCountryCode())
                        .user(user)
                        .build()));
            });
        }
        user.setName(userPresenter.getName());
        user.setEmail(userPresenter.getEmail());
        user.setPassword(userPresenter.getPassword());
        user.setCreated(isNewUser ? new Date() : user.getCreated());
        user.setModified(new Date());
        if (isNewUser) {
            user.setLastLogin(user.getCreated());
        }
        user.setPhones(phones);
        user.setToken(JWTUtils.addToken(userPresenter));
        User userSaved = userRepository.save(user);
        return toPresenter(userSaved);
    }

    @Override
    public void deleteUserById(UUID userId) {

    }

    private UserPresenter toPresenter(User user) {

        UserPresenter userPresenter = UserPresenter.builder()
                .id(user.getId())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isActive(user.getActive())
                .build();

        return userPresenter;
    }

    private void validateUserPresenter(UserPresenter userPresenter) {
        Pattern emailPattern;
        Pattern passwordPattern;
        if (userPresenter.getEmail() == null || userPresenter.getEmail().isEmpty() || userPresenter.getEmail().isBlank()) {
            throw new ValidationException("el usuario debe ingresar el email");
        }
        if (userPresenter.getPassword() == null || userPresenter.getPassword().isEmpty() || userPresenter.getPassword().isBlank()) {
            throw new ValidationException("el usuario debe ingresar la contraseña");
        }
        emailPattern = Pattern.compile(defaultConfigurationService.getDefaultConfigurationByName(DefaultConfigurationNames.DEFAULT_EMAIL_REGULAR_EXPRESSION.toString()).getValue());
        passwordPattern = Pattern.compile(defaultConfigurationService.getDefaultConfigurationByName(DefaultConfigurationNames.DEFAULT_PASSWORD_REGULAR_EXPRESSION.toString()).getValue());
        if (!emailPattern.matcher(userPresenter.getEmail()).matches()) {
            throw new ValidationException("El email no cumple el patrón establecido: 'alguien@dominio.com'");
        }
        if (!passwordPattern.matcher(userPresenter.getPassword()).matches()) {
            throw new ValidationException("La contraseña debe tener entre 8 y 16 caracteres, al menos un dígito, una minúscula y una mayúscula: 'Passw0rd'");
        }

    }
}
