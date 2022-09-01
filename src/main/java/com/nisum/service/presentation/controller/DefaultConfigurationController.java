package com.nisum.service.presentation.controller;

import com.nisum.service.entity.DefaultConfiguration;
import com.nisum.service.presentation.presenter.DefaultConfigurationPresenter;
import com.nisum.service.presentation.presenter.UserPresenter;
import com.nisum.service.service.DefaultConfigurationService;
import com.nisum.service.service.impl.DefaultConfigurationServiceImpl;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Generated
@RestController
public class DefaultConfigurationController {

    @Autowired
    private DefaultConfigurationService defaultConfigurationService;

    @PostMapping("/saveUpdatePattern")
    public DefaultConfiguration saveUpdatePattern(@RequestBody DefaultConfigurationPresenter defaultConfigurationPresenter) {
        return defaultConfigurationService.saveUpdatePattern(defaultConfigurationPresenter);
    }
}
