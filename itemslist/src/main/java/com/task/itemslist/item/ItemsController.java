package com.task.itemslist.item;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemsController {

    private final ItemService itemService;

    @Autowired
    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    // POST request to load sample items into the database
   @PostMapping("/load")
    public String saveItems() {
        itemService.saveItems();  // Save items to the database
        return "Items added to database successfully";
    }

    // GET request to fetch all items from the map
    @GetMapping("/total")
    public Map<Integer, String> getTotalItems() {
        itemService.getItems();  // Populate the map with items from the database
        return itemService.getTotalItems();
    }
    
    @GetMapping("/{id}")
    public String getItemById(@PathVariable int id) {
        return itemService.getItemById(id);
    }

}
