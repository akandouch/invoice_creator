package com.akandouch.invoicec.repository;

import com.akandouch.invoicec.domain.Settings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends MongoRepository<Settings, String>{
}
