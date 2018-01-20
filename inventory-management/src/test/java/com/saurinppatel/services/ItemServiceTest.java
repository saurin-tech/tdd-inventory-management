package com.saurinppatel.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.saurinppatel.domain.Item;
import com.saurinppatel.exception.ItemNotFoundException;
import com.saurinppatel.repositories.ItemRepository;

/*
 * this is going to be a very light test, so we are not going to involve spring
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

	@Mock
	private ItemRepository itemRepository;

	private ItemService itemService;

	@Before
	public void setUp() throws Exception {
		// Trying to keep spring out of this so the test is light
		// That's why we are using new and not dependency injection
		itemService = new ItemService(itemRepository);
	}

	@Test
	public void getItemDetails_ReturnsItemInfo() {
		given(itemRepository.findByName("vanilla")).willReturn(new Item("vanilla", "ice cream"));

		Item item = itemService.getItemDetails("vanilla");

		assertThat(item.getName()).isEqualTo("vanilla");
		assertThat(item.getType()).isEqualTo("ice cream");
	}

	@Test(expected = ItemNotFoundException.class)
	public void getItemDetails_WhenItemNotFound() throws Exception {
		given(itemRepository.findByName("vanilla")).willReturn(null);

		itemService.getItemDetails("vanilla");
	}
}
