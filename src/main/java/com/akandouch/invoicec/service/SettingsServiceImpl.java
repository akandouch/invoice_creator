package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Settings;
import com.akandouch.invoicec.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class SettingsServiceImpl implements SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public Settings getSettings() {
        return getSettings(Settings::new);
    }

    private Settings getSettings(Supplier<Settings> orElse) {
        return settingsRepository.findAll().stream().findFirst().orElseGet(orElse);
    }

    @Override
    public Settings saveOrUpdateSettings(Settings settings) {
        return settingsRepository.save(getSettings(() -> settings).toBuilder()
                .currentInvoiceNumber(settings.getCurrentInvoiceNumber())
                .currentRate(settings.getCurrentRate())
                .currentVatRate(settings.getCurrentVatRate())
                .build());
    }
}
