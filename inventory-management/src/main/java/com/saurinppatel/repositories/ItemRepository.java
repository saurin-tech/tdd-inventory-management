package com.saurinppatel.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.saurinppatel.domain.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

	Item findByName(String name);

}
