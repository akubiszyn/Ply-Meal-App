package com.example.demo;

import com.example.demo.Food.FoodController;
import com.example.demo.Food.ingredient.Ingredient;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Locale;

public class ShoppingList extends JFrame {
    private JPanel shoppingListMainFrame;
    private JPanel shoppingListTitlePanel;
    private JLabel tittle;
    private JList itemList;
    private JButton removeItemButton;
    private JTextField enterItem;
    private JButton addItemButton;
    private JScrollPane itemListScroll;

    public ShoppingList() {
        super();
        itemListScroll.setViewportView(itemList);
        itemList.setLayoutOrientation(JList.VERTICAL);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(shoppingListMainFrame);
        DefaultListModel listModel = new DefaultListModel<>();
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select name from shopping_list join food using (item_id)");) {
            while (rs.next()) {
                listModel.addElement(rs.getString(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        itemList.setModel(listModel);
        this.setVisible(true);

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pattern = enterItem.getText();
                FoodController foodController = new FoodController();
                Ingredient ingredient = foodController.getIngredient(pattern, "100", "gram");
                String foodName = ingredient.getName();
                int foodId = ingredient.getId();


                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();) {
                    try (ResultSet rsFood = stmt.executeQuery("insert into food values(" + foodId + ", '" + foodName + "')");) {
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        try (ResultSet rsList = stmt.executeQuery("insert into shopping_list values(" + foodId + ", 1)")) {
                            listModel.addElement(foodName);
                        } catch (SQLIntegrityConstraintViolationException exc) {
                            ;
                        }
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

//                model.addElement("aaaaa");
                itemList.setModel(listModel);
                enterItem.setText("");
            }
        });
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedItem = itemList.getSelectedIndex();
                if (selectedItem != -1) {

                    try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("Delete from shopping_list where item_id = (select item_id from food where name like '" + listModel.getElementAt(selectedItem) + "')");) {
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    listModel.remove(selectedItem);
                }
                itemList.setModel(listModel);
            }
        });
    }


    ;

}

