package com.akandouch.invoicec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akandouch.invoicec.domain.Item;
import com.akandouch.invoicec.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemRepository itemRepo;
	
	
	public List<Item> findAll() {
		return itemRepo.findAll();
	}
	
	public Item save(Item item) {
		return itemRepo.save(item);
	}

}
