package com.mycompany.inventorysystem;

public class InventoryItem {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;

    public InventoryItem(int id, String name, int quantity, double price, String description) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }

    // Static method to create 20 items for the inventory
    public static InventoryItem[] getInventoryItems() {
        return new InventoryItem[] {
            new InventoryItem(1, "Almonds", 100, 10.99, "Fresh almonds rich in nutrients."),
            new InventoryItem(2, "Groundnuts", 50, 5.49, "High-protein groundnuts."),
            new InventoryItem(3, "Walnuts", 75, 12.99, "Organic walnuts for brain health."),
            new InventoryItem(4, "Cashews", 120, 15.99, "Roasted cashews with a hint of salt."),
            new InventoryItem(5, "Pistachios", 60, 20.99, "Premium quality pistachios."),
            new InventoryItem(6, "Pecans", 40, 18.49, "Delicious pecans, perfect for baking."),
            new InventoryItem(7, "Sunflower Seeds", 80, 4.99, "Healthy snack with vitamins."),
            new InventoryItem(8, "Pumpkin Seeds", 100, 3.99, "Full of nutrients and minerals."),
            new InventoryItem(9, "Chia Seeds", 90, 8.99, "Rich in Omega-3 and fiber."),
            new InventoryItem(10, "Flax Seeds", 110, 6.99, "Great source of Omega-3."),
            new InventoryItem(11, "Macadamia Nuts", 30, 22.99, "Buttery and crunchy macadamia."),
            new InventoryItem(12, "Brazil Nuts", 50, 19.99, "Excellent source of selenium."),
            new InventoryItem(13, "Hazelnuts", 85, 14.99, "Perfect for snacking and baking."),
            new InventoryItem(14, "Pine Nuts", 40, 25.99, "Rich in vitamins and good fats."),
            new InventoryItem(15, "Coconut Chips", 75, 5.99, "Lightly toasted coconut chips."),
            new InventoryItem(16, "Raisins", 100, 3.49, "Sun-dried natural raisins."),
            new InventoryItem(17, "Dried Apricots", 60, 7.99, "Sweet and chewy dried apricots."),
            new InventoryItem(18, "Dried Figs", 50, 9.99, "Naturally sweet and chewy figs."),
            new InventoryItem(19, "Dates", 70, 11.99, "Medjool dates, perfect for desserts."),
            new InventoryItem(20, "Cranberries", 85, 6.99, "Dried cranberries, rich in antioxidants.")
        };
    }
}
