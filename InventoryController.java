package com.mycompany.inventorysystem;

import java.util.ArrayList;
import java.util.List;

public class InventoryController {
    private List<InventoryItem> inventoryItems;

    public InventoryController() {
        inventoryItems = new ArrayList<>();
        loadItems();
    }

    private void loadItems() {
        InventoryItem[] items = InventoryItem.getInventoryItems();
        for (InventoryItem item : items) {
            inventoryItems.add(item);
        }
    }

    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }
}
