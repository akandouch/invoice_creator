package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Settings;

public interface SettingsService {
    Settings getSettings();
    Settings saveOrUpdateSettings(Settings settings);
}
