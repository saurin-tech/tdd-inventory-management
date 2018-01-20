package com.saurinppatel.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.saurinppatel.domain.Item;
import com.saurinppatel.exception.ItemNotFoundException;
import com.saurinppatel.repositories.ItemRepository;

@Service
public class ItemService {

	private ItemRepository itemRepository;

	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Cacheable("items")
	public Item getItemDetails(String name) {
		Item item = itemRepository.findByName(name);
		if (item == null) {
			throw new ItemNotFoundException();
		}
		return item;
	}

}
