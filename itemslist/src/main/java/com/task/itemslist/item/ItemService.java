package com.task.itemslist.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
class ItemService {

	private final ItemRepository itemRepository;
	private final Map<Integer, String> itemMap = new HashMap<>();

	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

//	Method to save static items to the database
	public void saveItems() {
		List<Items> itemsToSave = new ArrayList<>();
		itemsToSave.add(new Items(1, "Chair", "number", 20));
		itemsToSave.add(new Items(2, "Laptop", "number", 30));
		itemsToSave.add(new Items(3, "Apple", "kilos", 45));
		itemsToSave.add(new Items(4, "TV", "inches", 10));

//		Save items to the database
		itemRepository.saveAll(itemsToSave);
//		System.out.println("saved");
	}

//	 Method to retrieve items from the database
	public void getItems() {
		List<Items> items = itemRepository.findAll();
//		itemMap.clear(); 
		for (Items item : items) {
			itemMap.put(item.getId(), item.getName()); 
		}
	}

	public Map<Integer, String> getTotalItems() {
		return itemMap; 
	}

	public String getItemById(int id) {
	    return itemMap.getOrDefault(id, "Item not found");
	}


}
