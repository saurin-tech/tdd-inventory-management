package com.saurinppatel.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.saurinppatel.services.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private ItemService itemService;

	@Test
	public void getItem_returnsItemDetails() {

		// arrange
		org.mockito.BDDMockito.given(itemService.getItemDetails(anyString()))
				.willReturn(new Item("vanilla", "ice cream", 10, 3.0, UnitsOfMeasurements.GALLONS));

		// act <- interact, behavior
		ResponseEntity<Item> response = restTemplate.getForEntity("/items/vanilla", Item.class);

		// assert <- asserting
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getName()).isEqualTo("vanilla");
		assertThat(response.getBody().getType()).isEqualTo("ice cream");
		assertThat(response.getBody().getCurrentQuantity()).isEqualTo(10);
		assertThat(response.getBody().getSize()).isEqualTo(3.0);
		assertThat(response.getBody().getUnits()).isEqualTo(UnitsOfMeasurements.GALLONS);

	}

}
