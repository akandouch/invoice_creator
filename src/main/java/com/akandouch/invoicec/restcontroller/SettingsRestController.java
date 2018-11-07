package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.domain.Settings;
import com.akandouch.invoicec.service.SettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/settings")
@CrossOrigin(value = "*", allowedHeaders = "*", exposedHeaders = "x-auth-token")
@Slf4j
public class SettingsRestController {


    @Autowired
     private SettingsService settingsService;


    @GetMapping
    public Settings getSettings() {
        return this.settingsService.getSettings();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Settings save(@Valid @RequestBody Settings settings) {
        return settingsService.saveOrUpdateSettings(settings);
    }
}
