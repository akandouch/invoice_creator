package com.akandouch.invoicec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.akandouch.invoicec.domain.Item;

public interface ItemRepository extends MongoRepository<Item, Long>{

}
