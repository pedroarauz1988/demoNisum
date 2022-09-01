package com.nisum.service.service;


import com.nisum.service.entity.DefaultConfiguration;
import com.nisum.service.presentation.presenter.DefaultConfigurationPresenter;

public interface DefaultConfigurationService {

    DefaultConfiguration getDefaultConfigurationByName(String name);

    DefaultConfiguration saveUpdatePattern(DefaultConfigurationPresenter defaultConfigurationPresenter);

}
