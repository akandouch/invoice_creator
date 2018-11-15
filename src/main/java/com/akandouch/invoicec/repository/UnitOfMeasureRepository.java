package com.akandouch.invoicec.repository;

import com.akandouch.invoicec.domain.UnitOfMeasure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitOfMeasureRepository extends MongoRepository<UnitOfMeasure,String> {
}
