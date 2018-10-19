package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Settings;
import com.akandouch.invoicec.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImpl implements SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public Settings  getSettings() {
        return settingsRepository.findAll().stream().findAny().orElseGet(Settings::new);
    }

    @Override
    public Settings saveOrUpdateSettings(Settings settings) {
        return settingsRepository.save(getSettings().toBuilder()
                .currentInvoiceNumber(settings.getCurrentInvoiceNumber())
                .id(settings.getId())
                .build());
    }
}
