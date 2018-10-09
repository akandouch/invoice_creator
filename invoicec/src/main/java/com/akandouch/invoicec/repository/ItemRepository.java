package com.akandouch.invoicec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.akandouch.invoicec.domain.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, Long>{

}
