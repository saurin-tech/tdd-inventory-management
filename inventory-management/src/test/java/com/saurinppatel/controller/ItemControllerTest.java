package com.saurinppatel.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.saurinppatel.domain.Item;
import com.saurinppatel.exception.ItemNotFoundException;
import com.saurinppatel.services.ItemService;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class) // Does not start entire spring boot app. Only wires the Item Controller.
public class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemService itemService;

	@Test
	public void getItem_ShouldReturnItem() throws Exception {
		// mocking ItemService
		// using anyString() to just make sure you are getting something back (because
		// we are testing url not data)
		given(itemService.getItemDetails(anyString())).willReturn(new Item("vanilla", "ice cream"));

		mockMvc.perform(MockMvcRequestBuilders.get("/items/vanilla")).andExpect(status().isOk())
				.andExpect(jsonPath("name").value("vanilla")).andExpect(jsonPath("type").value("ice cream"));
	}

	@Test
	public void getItem_NotFound() throws Exception {
		given(itemService.getItemDetails(anyString())).willThrow(new ItemNotFoundException());

		mockMvc.perform(MockMvcRequestBuilders.get("/items/vanilla")).andExpect(status().isNotFound());
	}

}
