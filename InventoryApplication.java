package com.mycompany.inventorysystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryApplication extends JFrame {
    private JTable inventoryTable;
    private DefaultTableModel tableModel;
    private InventoryController controller;

    public InventoryApplication() {
        controller = new InventoryController();
        initializeUI();
        loadInventoryData();
    }

    private void initializeUI() {
        setTitle("Inventory Management");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Quantity", "Price", "Description"}, 0);
        inventoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        add(scrollPane, BorderLayout.CENTER);

        // Set up buttons
        JPanel buttonPanel = new JPanel();
        JButton logoutButton = new JButton("Logout");
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to logout
                dispose();
                new LoginGUI().setVisible(true); // Show the login screen
            }
        });
    }

    private void loadInventoryData() {
        for (InventoryItem item : controller.getInventoryItems()) {
            tableModel.addRow(new Object[]{
                item.getId(),
                item.getName(),
                item.getQuantity(),
                item.getPrice(),
                item.getDescription()
            });
        }
    }

    public static void main(String[] args) {
        // First, show the login screen
        SwingUtilities.invokeLater(() -> {
            LoginGUI loginGUI = new LoginGUI();
            loginGUI.setVisible(true);
        });
    }
}
