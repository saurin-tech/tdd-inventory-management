package com.saurinppatel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.saurinppatel.domain.Item;
import com.saurinppatel.repositories.ItemRepository;
import com.saurinppatel.services.ItemService;

@RunWith(SpringRunner.class)
// You need to have SpringBootTest to test for caching but you don't need a
// server so we can say that the
// webEnviorment is none, because we are not testing against a server. we just
// need application start up to
// detect that there is enable caching annotation on the application.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
// exclude auto configuration to run a test against a real database
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@AutoConfigureCache
public class CachingTest {

	@Autowired
	private ItemService itemService;

	@MockBean
	private ItemRepository itemRepository;

	// Testing behavior of caching
	@Test
	public void caching() throws Exception {
		org.mockito.BDDMockito.given(itemRepository.findByName(ArgumentMatchers.anyString()))
				.willReturn(new Item("vanilla", "ice cream"));

		itemService.getItemDetails("vanilla");
		itemService.getItemDetails("vanilla");

		org.mockito.Mockito.verify(itemRepository, org.mockito.Mockito.times(1)).findByName("vanilla");
	}
}
