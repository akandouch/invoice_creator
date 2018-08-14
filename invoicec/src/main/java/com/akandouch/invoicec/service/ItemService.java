package com.akandouch.invoicec.service;

import java.util.List;

import com.akandouch.invoicec.domain.Item;

public interface ItemService {

	List<Item> findAll();
	Item save(Item item);
}
