package com.example.demo;
import com.example.demo.Food.FoodController;
import com.example.demo.Food.ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class Fridge extends JFrame {
    private JPanel fridgeMainFrame;
    private JPanel fridgeTitlePanel;
    private JLabel tittle;
    private JList itemList;
    private JButton addItemButton;
    private JTextField enterItem;
    private JButton removeItemButton;
    private DefaultListModel listModel;

    public Fridge() {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(fridgeMainFrame);
        DefaultListModel listModel = new DefaultListModel<>();
        this.setVisible(true);
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pattern = enterItem.getText();
                FoodController foodController = new FoodController();
                Ingredient ingredient = foodController.getIngredient(pattern, "100", "gram");
                String foodName = ingredient.getName();
                String foodImage = ingredient.getImage();
                String sqlAddFood = "Insert into food values ('" + foodName + "', '" + foodImage + "');";
                System.out.println(foodName);
                System.out.println(foodImage);
                listModel.addElement(foodName);
//                jdbcTemplate.update(sqlAddFood);
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
                    listModel.remove(selectedItem);
                }
                itemList.setModel(listModel);
            }
        });
    }

    ;


}
