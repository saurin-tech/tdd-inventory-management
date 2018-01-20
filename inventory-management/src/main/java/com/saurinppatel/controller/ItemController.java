package com.saurinppatel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.saurinppatel.domain.Item;
import com.saurinppatel.services.ItemService;

@RestController
// @RequestMapping("/items")
public class ItemController {

	private ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping("/items/{name}")
	private Item getItem(@PathVariable String name) {
		return itemService.getItemDetails(name);
	}

	/*
	 * This is handled in the exception it self so we don't need to put a handler
	 * here.
	 */
	/*
	 * @ExceptionHandler
	 * 
	 * @ResponseStatus(HttpStatus.NOT_FOUND) private void
	 * itemNotFoundHandler(ItemNotFoundException ex) {
	 * 
	 * }
	 */
}
