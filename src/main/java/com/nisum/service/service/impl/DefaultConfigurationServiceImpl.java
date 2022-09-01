package com.nisum.service.service.impl;


import com.nisum.service.entity.DefaultConfiguration;
import com.nisum.service.exception.ValidationException;
import com.nisum.service.presentation.presenter.DefaultConfigurationPresenter;
import com.nisum.service.repository.DefaultConfigurationRepository;
import com.nisum.service.service.DefaultConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DefaultConfigurationServiceImpl implements DefaultConfigurationService {
    @Autowired
    private DefaultConfigurationRepository defaultConfigurationRepository;

    @Override
    public DefaultConfiguration getDefaultConfigurationByName(String name) {
        Optional<DefaultConfiguration> defaultConfiguration = defaultConfigurationRepository.findByName(name);
        if (!defaultConfiguration.isPresent()) {
            throw new ValidationException("Debe crear la configuración para " + name);
        }
        return defaultConfiguration.get();
    }

    @Override
    public DefaultConfiguration saveUpdatePattern(DefaultConfigurationPresenter defaultConfigurationPresenter) {
        Optional<DefaultConfiguration> defaultConfigurationOptional = defaultConfigurationRepository.findByName(defaultConfigurationPresenter.getName());
        DefaultConfiguration defaultConfiguration = new DefaultConfiguration();
        if (defaultConfigurationOptional.isPresent()) {
            defaultConfiguration.setPattern(defaultConfigurationPresenter.getPattern());
        } else {
            defaultConfiguration.setName(defaultConfigurationPresenter.getName());
            defaultConfiguration.setPattern(defaultConfigurationPresenter.getPattern());
        }
        DefaultConfiguration defaultConfigurationSaved = defaultConfigurationRepository.save(defaultConfiguration);
        return defaultConfigurationSaved;
    }
}
