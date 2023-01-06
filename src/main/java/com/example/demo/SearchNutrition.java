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
    private JPanel typeProduct;
    private JTextField typeProductText;
    private JPanel typeRecipe;

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
        nutritionMainFrame.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(13, 7, new Insets(0, 0, 0, 0), -1, -1));
        nutritionMainFrame.setBackground(new Color(-15946596));
        nutritionMainFrame.setForeground(new Color(-16777216));
        nutritionMainFrame.setMinimumSize(new Dimension(212, 300));
        searchNutritionTitle = new JLabel();
        searchNutritionTitle.setBackground(new Color(-5570596));
        searchNutritionTitle.setForeground(new Color(-16777216));
        searchNutritionTitle.setText("Search Nutrients for a product");
        nutritionMainFrame.add(searchNutritionTitle, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 48), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setForeground(new Color(-16777216));
        label1.setText("Type product...");
        nutritionMainFrame.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        nutritionMainFrame.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(11, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        product = new JTextField();
        product.setBackground(new Color(-4473925));
        nutritionMainFrame.add(product, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 2, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setForeground(new Color(-16777216));
        label2.setText("Type amount...(gram)");
        nutritionMainFrame.add(label2, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        amount = new JTextField();
        amount.setBackground(new Color(-4473925));
        nutritionMainFrame.add(amount, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        buttonOK = new JButton();
        buttonOK.setBackground(new Color(-5570596));
        buttonOK.setLabel("OK");
        buttonOK.setText("OK");
        nutritionMainFrame.add(buttonOK, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        nutritionsLabel = new JLabel();
        nutritionsLabel.setAutoscrolls(true);
        nutritionsLabel.setBackground(new Color(-15946596));
        nutritionsLabel.setHorizontalTextPosition(10);
        nutritionsLabel.setText("");
        nutritionMainFrame.add(nutritionsLabel, new com.intellij.uiDesigner.core.GridConstraints(12, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(0, 160), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return nutritionMainFrame;
    }
}
