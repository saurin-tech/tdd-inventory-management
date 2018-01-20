package com.saurinppatel.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.DBCollection;
import com.saurinppatel.domain.Item;

/*
 * DataMongoTest annotation gives us the ability to do the autowiring with the repository,
 * so basically it loads JPA type of context for mongo.  
 * It does not load web components or any other unnecessary components.  
 * All the transactions will be reversed unless you use
 * @Transactional(propagation = Propagation.NOT_SUPPORTED)
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;

	// TestEntityManager is used for JPA based databases
	// For mongo use MongoTemplate
	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void checkMongoTemplate() {
		assertNotNull(mongoTemplate);
		DBCollection createCollection = mongoTemplate.createCollection("Item");
		assertTrue(mongoTemplate.collectionExists("Item"));
	}

	@Test
	public void getItem_ReturnsItemDetails() throws Exception {

		Item savedItem = new Item("vanilla", "ice cream");
		mongoTemplate.save(savedItem);
		Item item = itemRepository.findByName("vanilla");

		assertThat(item.getName()).isEqualTo(savedItem.getName());
		assertThat(item.getType()).isEqualTo(savedItem.getType());
	}
}
