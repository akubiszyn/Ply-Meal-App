package com.example.demo;

import com.example.demo.Food.FoodController;
import com.example.demo.Food.ingredient.Ingredient;
import com.example.demo.Food.ingredient.Nutrients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchNutrition extends JFrame {


    private JPanel nutritionMainFrame;
    private JLabel searchNutritionTitle;
    private JTextField product;
    private JTextField amount;
    private JButton buttonOK;
    private JLabel nutritionsLabel;

    public SearchNutrition() {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(nutritionMainFrame);
        this.setVisible(true);
        nutritionsLabel.setSize(700, 100);
        this.buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String food = product.getText();
                String strAmount = amount.getText();
                FoodController foodController = new FoodController();
                Ingredient ingredient = foodController.getIngredient(food, strAmount, "gram");
                String finalText = ingredient.getName() + ": ";
                ArrayList<Nutrients> nutrientsList = ingredient.getNutrition().getNutrients();
                for (Nutrients nutrient : nutrientsList) {
                    if (nutrient.getName().matches("Calories|Fat|Carbohydrates|Protein")) {
                        String name = nutrient.getName();
                        finalText += nutrient.getName() + ": " + nutrient.getAmount().toString() + " " + nutrient.getUnit() + " ";
                    }
                }

                nutritionsLabel.setText(finalText);

            }
        });

    }

}
