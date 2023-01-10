package com.example.demo;

import com.example.demo.Food.FoodController;
import com.example.demo.Food.ingredient.Ingredient;
import com.example.demo.Food.ingredient.Nutrients;
import com.example.demo.Food.recipe.nutrients.RecipeNutrients;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class SearchNutrition extends JFrame {


    private JPanel nutritionMainFrame;
    private JLabel searchNutritionTitle;
    private JTextField product;
    private JTextField amount;
    private JButton buttonOK2;
    private JLabel productNutrients;
    private JLabel caloriesLabel;
    private JLabel fatLabel;
    private JLabel carbsLabel;
    private JLabel proteinLabel;
    private JButton buttonOK1;
    private JComboBox comboBox1;
    private JLabel calorieLabel1;
    private JLabel fatLabel1;
    private JLabel carbsLabel1;
    private JLabel proteinLabel1;
    private JComboBox comboBox2;


    public SearchNutrition() {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(nutritionMainFrame);
        this.setVisible(true);
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select name from recipe");) {
            while (rs.next()) {
                String recipe = rs.getString(1);
                comboBox1.addItem(recipe);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        this.buttonOK1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Nutrients> chosenNutrients = new ArrayList<>();
                String food = product.getText();
                String strAmount = amount.getText();
                String unit = comboBox2.getSelectedItem().toString();
                FoodController foodController = new FoodController();
                Ingredient ingredient = foodController.getIngredient(food, strAmount, unit);
                ArrayList<Nutrients> nutrientsList = ingredient.getNutrition().getNutrients();
                for (Nutrients nutrient : nutrientsList) {
                    if (nutrient.getName().matches("Calories|Fat|Carbohydrates|Protein")) {
                        chosenNutrients.add(nutrient);
                    }
                }
                for (Nutrients nutrient : chosenNutrients) {
                    String finalText = new String();
                    finalText = nutrient.getName() + ": " + nutrient.getAmount().toString() + " " + nutrient.getUnit();
                    switch (nutrient.getName()) {
                        case "Calories":
                            caloriesLabel.setText(finalText);
                            break;
                        case "Fat":
                            fatLabel.setText(finalText);
                            break;
                        case "Carbohydrates":
                            carbsLabel.setText(finalText);
                            break;
                        case "Protein":
                            proteinLabel.setText(finalText);
                            break;
                    }
                }


            }
        });
//        RecipeNutrients recipeNutrients = foodController.getRecipeNutrients(rs.getInt(1));
//        "Select recipe_id from recipe where name = recipeName"
        buttonOK2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipeName = comboBox1.getSelectedItem().toString();
                FoodController foodController = new FoodController();
                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("Select recipe_id from recipe where name = \'" + recipeName + "\'");) {
                    while (rs.next()) {
                        RecipeNutrients recipeNutrients = foodController.getRecipeNutrients(rs.getInt(1));
                        calorieLabel1.setText("Calories: " + recipeNutrients.getCalories());
                        fatLabel1.setText("Fat: " + recipeNutrients.getFat());
                        carbsLabel1.setText("Carbohydrates: " + recipeNutrients.getCarbs());
                        proteinLabel1.setText("Protein: " + recipeNutrients.getProtein());
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

}
