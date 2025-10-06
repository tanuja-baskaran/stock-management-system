package com.mycompany.inventorysystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InventoryGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private List<String> cart = new ArrayList<>(); // List to hold cart items
    private JTextField searchField;
    private JTextField quantityField;

    public InventoryGUI() {
        setTitle("Inventory Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a table model and set it to the JTable
        model = new DefaultTableModel(new String[]{"ID", "Name", "Quantity", "Price", "Description"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Create a search bar and buttons
        searchField = new JTextField(10);
        quantityField = new JTextField(5);
        JButton searchButton = new JButton("Search");
        JButton addToCartButton = new JButton("Add to Cart");
        JButton viewCartButton = new JButton("View Cart");
        JButton purchaseButton = new JButton("Purchase");
        JButton logoutButton = new JButton("Logout");

        // Add components to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Search:"));
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(addToCartButton);
        panel.add(viewCartButton);
        panel.add(purchaseButton);
        panel.add(logoutButton);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Load inventory data
        loadInventoryData();

        // Search functionality
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchItem(searchField.getText());
            }
        });

        // Add to cart functionality
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String itemName = model.getValueAt(selectedRow, 1).toString();
                    int quantity = Integer.parseInt(quantityField.getText());
                    cart.add(itemName + " (Quantity: " + quantity + ")"); // Add selected item to cart
                    JOptionPane.showMessageDialog(InventoryGUI.this, itemName + " added to cart with quantity: " + quantity);
                } else {
                    JOptionPane.showMessageDialog(InventoryGUI.this, "Please select an item to add to cart.");
                }
            }
        });

        // View cart functionality
        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCart();
            }
        });

        // Purchase functionality
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseItems();
            }
        });

        // Logout functionality
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close inventory GUI
                new LoginGUI().setVisible(true); // Open login GUI
            }
        });
    }

    private void loadInventoryData() {
        // Sample data for the inventory
        Object[][] items = {
            {1, "Groundnut", 100, 50.0, "Rich in protein and healthy fats"},
            {2, "Almonds", 80, 150.0, "High in vitamin E and magnesium"},
            {3, "Walnuts", 60, 200.0, "Contains omega-3 fatty acids"},
            {4, "Cashews", 90, 120.0, "Rich in iron and zinc"},
            {5, "Pistachios", 70, 160.0, "Good source of protein and fiber"},
            {6, "Hazelnuts", 40, 180.0, "High in antioxidants"},
            {7, "Brazil Nuts", 30, 220.0, "Excellent source of selenium"},
            {8, "Macadamia Nuts", 20, 250.0, "Rich in monounsaturated fats"},
            {9, "Peanuts", 200, 40.0, "High in protein and healthy fats"},
            {10, "Chestnuts", 150, 60.0, "Low in fat and high in fiber"},
            {11, "Flaxseeds", 120, 70.0, "High in omega-3 fatty acids"},
            {12, "Pumpkin Seeds", 110, 90.0, "Good source of magnesium"},
            {13, "Chia Seeds", 130, 130.0, "Rich in omega-3 fatty acids"},
            {14, "Sunflower Seeds", 140, 40.0, "High in vitamin E"},
            {15, "Sesame Seeds", 50, 100.0, "Good source of calcium"},
            {16, "Hemp Seeds", 60, 200.0, "Rich in protein and omega-3"},
            {17, "Poppy Seeds", 80, 80.0, "Contains calcium and iron"},
            {18, "Watermelon Seeds", 90, 60.0, "High in magnesium"},
            {19, "Safflower Seeds", 70, 30.0, "Good source of healthy fats"},
            {20, "Cucumber Seeds", 40, 50.0, "Rich in vitamins and minerals"}
        };

        for (Object[] item : items) {
            model.addRow(item);
        }
    }

    private void searchItem(String query) {
        // Search logic to find the item by ID or name
        for (int i = 0; i < model.getRowCount(); i++) {
            String itemName = model.getValueAt(i, 1).toString();
            String itemId = model.getValueAt(i, 0).toString();
            if (itemName.equalsIgnoreCase(query) || itemId.equals(query)) {
                table.setRowSelectionInterval(i, i); // Select the row if found
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Item not found.");
    }

    private void viewCart() {
        StringBuilder cartContents = new StringBuilder("Items in Cart:\n");
        for (String item : cart) {
            cartContents.append(item).append("\n");
        }
        JOptionPane.showMessageDialog(this, cartContents.toString());
    }

    private void purchaseItems() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty. Please add items to the cart before purchasing.");
            return;
        }

        double totalPrice = 0.0;
        StringBuilder purchaseDetails = new StringBuilder("Purchase Summary:\n");
        for (String item : cart) {
            String[] parts = item.split(" \\(Quantity: ");
            String itemName = parts[0];
            int quantity = Integer.parseInt(parts[1].replaceAll("\\)", ""));
            // Find price from the table (assuming prices are loaded in the model)
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 1).equals(itemName)) {
                    double price = (double) model.getValueAt(i, 3);
                    totalPrice += price * quantity;
                    purchaseDetails.append(itemName).append(" - Quantity: ").append(quantity)
                            .append(" - Price: ").append(price * quantity).append("\n");
                }
            }
        }
        purchaseDetails.append("Total Price: ").append(totalPrice);
        JOptionPane.showMessageDialog(this, purchaseDetails.toString());
        cart.clear(); // Clear the cart after purchase
    }
}
