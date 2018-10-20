package com.akandouch.invoicec.repository;

import com.akandouch.invoicec.domain.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    List<Item> findAllByPeriodFromYearOrderByPeriodFromMonth(int year);
}
