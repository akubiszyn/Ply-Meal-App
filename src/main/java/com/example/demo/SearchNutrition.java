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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        nutritionMainFrame = new JPanel();
        nutritionMainFrame.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(18, 5, new Insets(0, 0, 0, 0), -1, -1));
        nutritionMainFrame.setBackground(new Color(-15946596));
        nutritionMainFrame.setForeground(new Color(-16777216));
        nutritionMainFrame.setMinimumSize(new Dimension(212, 300));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        nutritionMainFrame.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 5, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        nutritionMainFrame.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(15, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        searchNutritionTitle = new JLabel();
        searchNutritionTitle.setBackground(new Color(-5570596));
        Font searchNutritionTitleFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 22, searchNutritionTitle.getFont());
        if (searchNutritionTitleFont != null) searchNutritionTitle.setFont(searchNutritionTitleFont);
        searchNutritionTitle.setForeground(new Color(-5570596));
        searchNutritionTitle.setText("SEARCH NUTRIENTS FOR A PRODUCT OR A RECIPE");
        nutritionMainFrame.add(searchNutritionTitle, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 48), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-5570596));
        panel1.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, new Dimension(-1, 30), 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        productNutrients = new JLabel();
        productNutrients.setBackground(new Color(-16777216));
        Font productNutrientsFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 18, productNutrients.getFont());
        if (productNutrientsFont != null) productNutrients.setFont(productNutrientsFont);
        productNutrients.setForeground(new Color(-15946596));
        productNutrients.setText("NUTRIENTS FOR A PRODUCT");
        panel1.add(productNutrients, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-5570596));
        Font panel2Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, -1, panel2.getFont());
        if (panel2Font != null) panel2.setFont(panel2Font);
        panel2.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(300, -1), new Dimension(300, -1), new Dimension(300, 30), 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(null, "TYPE PRODUCT", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 24, panel2.getFont()), new Color(-15946596)));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-5570596));
        Font panel3Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, -1, panel3.getFont());
        if (panel3Font != null) panel3.setFont(panel3Font);
        panel3.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(12, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(300, -1), null, new Dimension(300, 50), 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(null, "SELECT RECIPE", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 24, panel3.getFont()), new Color(-15946596)));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-5570596));
        panel4.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        caloriesLabel = new JLabel();
        caloriesLabel.setBackground(new Color(-16777216));
        Font caloriesLabelFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 18, caloriesLabel.getFont());
        if (caloriesLabelFont != null) caloriesLabel.setFont(caloriesLabelFont);
        caloriesLabel.setForeground(new Color(-15946596));
        caloriesLabel.setText("CALORIES:");
        panel4.add(caloriesLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setBackground(new Color(-5570596));
        panel5.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(85, 31), null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        fatLabel = new JLabel();
        fatLabel.setBackground(new Color(-16777216));
        Font fatLabelFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 18, fatLabel.getFont());
        if (fatLabelFont != null) fatLabel.setFont(fatLabelFont);
        fatLabel.setForeground(new Color(-15946596));
        fatLabel.setText("FAT:");
        panel5.add(fatLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        product = new JTextField();
        product.setBackground(new Color(-9118745));
        Font productFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, product.getFont());
        if (productFont != null) product.setFont(productFont);
        product.setText("");
        nutritionMainFrame.add(product, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(300, -1), new Dimension(150, -1), new Dimension(300, -1), 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setBackground(new Color(-5570596));
        Font panel6Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, -1, panel6.getFont());
        if (panel6Font != null) panel6.setFont(panel6Font);
        panel6.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel6, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(300, -1), null, new Dimension(300, 50), 0, false));
        panel6.setBorder(BorderFactory.createTitledBorder(null, "TYPE AMOUNT", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 24, panel6.getFont()), new Color(-15946596)));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel7.setBackground(new Color(-5570596));
        panel7.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel7, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 5, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel7.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        carbsLabel = new JLabel();
        carbsLabel.setBackground(new Color(-16777216));
        Font carbsLabelFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 18, carbsLabel.getFont());
        if (carbsLabelFont != null) carbsLabel.setFont(carbsLabelFont);
        carbsLabel.setForeground(new Color(-15946596));
        carbsLabel.setText("CARBS:");
        panel7.add(carbsLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox1 = new JComboBox();
        nutritionMainFrame.add(comboBox1, new com.intellij.uiDesigner.core.GridConstraints(13, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonOK2 = new JButton();
        buttonOK2.setBackground(new Color(-5570596));
        buttonOK2.setLabel("OK");
        buttonOK2.setText("OK");
        nutritionMainFrame.add(buttonOK2, new com.intellij.uiDesigner.core.GridConstraints(14, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel8.setBackground(new Color(-5570596));
        panel8.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel8, new com.intellij.uiDesigner.core.GridConstraints(12, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel8.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-16777216));
        Font label1Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 18, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-15946596));
        label1.setText("NUTRIENTS FOR A RECIPE");
        panel8.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel9.setBackground(new Color(-5570596));
        panel9.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel9, new com.intellij.uiDesigner.core.GridConstraints(13, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel9.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        calorieLabel1 = new JLabel();
        calorieLabel1.setBackground(new Color(-16777216));
        Font calorieLabel1Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 18, calorieLabel1.getFont());
        if (calorieLabel1Font != null) calorieLabel1.setFont(calorieLabel1Font);
        calorieLabel1.setForeground(new Color(-15946596));
        calorieLabel1.setText("CALORIES:");
        panel9.add(calorieLabel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel10.setBackground(new Color(-5570596));
        panel10.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel10, new com.intellij.uiDesigner.core.GridConstraints(14, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel10.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        fatLabel1 = new JLabel();
        fatLabel1.setBackground(new Color(-16777216));
        Font fatLabel1Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 18, fatLabel1.getFont());
        if (fatLabel1Font != null) fatLabel1.setFont(fatLabel1Font);
        fatLabel1.setForeground(new Color(-15946596));
        fatLabel1.setText("FAT:");
        panel10.add(fatLabel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel11.setBackground(new Color(-5570596));
        panel11.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel11, new com.intellij.uiDesigner.core.GridConstraints(15, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel11.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        carbsLabel1 = new JLabel();
        carbsLabel1.setBackground(new Color(-16777216));
        Font carbsLabel1Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 18, carbsLabel1.getFont());
        if (carbsLabel1Font != null) carbsLabel1.setFont(carbsLabel1Font);
        carbsLabel1.setForeground(new Color(-15946596));
        carbsLabel1.setText("CARBS:");
        panel11.add(carbsLabel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel12.setBackground(new Color(-5570596));
        panel12.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel12, new com.intellij.uiDesigner.core.GridConstraints(16, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel12.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        proteinLabel1 = new JLabel();
        proteinLabel1.setBackground(new Color(-16777216));
        Font proteinLabel1Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 18, proteinLabel1.getFont());
        if (proteinLabel1Font != null) proteinLabel1.setFont(proteinLabel1Font);
        proteinLabel1.setForeground(new Color(-15946596));
        proteinLabel1.setText("PROTEIN:");
        panel12.add(proteinLabel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel13.setBackground(new Color(-5570596));
        panel13.setForeground(new Color(-15946596));
        nutritionMainFrame.add(panel13, new com.intellij.uiDesigner.core.GridConstraints(9, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel13.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        proteinLabel = new JLabel();
        proteinLabel.setBackground(new Color(-16777216));
        Font proteinLabelFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 18, proteinLabel.getFont());
        if (proteinLabelFont != null) proteinLabel.setFont(proteinLabelFont);
        proteinLabel.setForeground(new Color(-15946596));
        proteinLabel.setText("PROTEIN:");
        panel13.add(proteinLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        nutritionMainFrame.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        nutritionMainFrame.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(17, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        amount = new JTextField();
        amount.setBackground(new Color(-9118745));
        Font amountFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, amount.getFont());
        if (amountFont != null) amount.setFont(amountFont);
        amount.setText("");
        nutritionMainFrame.add(amount, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(300, -1), new Dimension(150, -1), new Dimension(300, -1), 0, false));
        comboBox2 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("gram");
        defaultComboBoxModel1.addElement("cups");
        defaultComboBoxModel1.addElement("pinch");
        defaultComboBoxModel1.addElement("");
        defaultComboBoxModel1.addElement("tbsps");
        defaultComboBoxModel1.addElement("tsps");
        comboBox2.setModel(defaultComboBoxModel1);
        nutritionMainFrame.add(comboBox2, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonOK1 = new JButton();
        buttonOK1.setBackground(new Color(-5570596));
        buttonOK1.setLabel("OK");
        buttonOK1.setText("OK");
        nutritionMainFrame.add(buttonOK1, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return nutritionMainFrame;
    }

}
