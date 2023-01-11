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
import java.util.ArrayList;
import java.util.Locale;

public class NewRecipe extends JFrame {
    private JPanel newRecipeMainFrame;
    private JLabel tittle;
    private JTextField titleField;
    private JTextField quantityField;
    private JTextField unitField;
    private JTextField ingredientField;
    private JButton enterIngredientButton;
    private JTextField stepField;
    private JButton enterStepButton;
    private JButton enterTitleButton;
    private JPanel panel;
    private JPanel titlePanel;

    private int new_recipe_id;

    private int new_step_number = 1;

    public NewRecipe() {
        super();
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(panel);

        String sqlQuery = "select max(recipe_id) from recipe where recipe_id < 1000";
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             //ResultSet rs = stmt.executeQuery("Select name from recipe where name like '%\" + key_word + \"%'");) {
             ResultSet rs = stmt.executeQuery(sqlQuery);) {
            while (rs.next()) {
                new_recipe_id = rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        this.setVisible(true);

        enterTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();

                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();) {
                    try (ResultSet rsFood = stmt.executeQuery("insert into recipe values(" + new_recipe_id + ", '" + title + "'," + null + ")");) {
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        ;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        enterIngredientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("adding ingredient");

                int quantity = Integer.parseInt(quantityField.getText());
                String unit = unitField.getText();
                String name = ingredientField.getText();
                int ingredient_id = 0;

                /*get from library*/
                int foodId = -1;
                String foodName = name;
                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();
                     ResultSet rs1 = stmt.executeQuery("select item_id from food where name like '%" + name + "%'");) {
                    if (rs1.next()) {
                        foodId = rs1.getInt(1);
                    } else {
                        FoodController foodController = new FoodController();
                        Ingredient ingredient = foodController.getIngredient(name, "100", "gram");
                        foodName = ingredient.getName();
                        foodId = ingredient.getId();
                        try (ResultSet insertFood = stmt.executeQuery("insert into food values(" + foodId + ", '" + foodName + "')");) {
                        } catch (SQLException ex) {
//                            throw new RuntimeException(ex);
                            System.out.println("not added to food");
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }





//                FoodController foodController = new FoodController();
//                Ingredient ingredient = foodController.getIngredient(name, "100", "gram");
//                String foodName = ingredient.getName();
//                int foodId = ingredient.getId();

                System.out.println("foodName: " + foodName);
                System.out.println("foodId: " + foodId);


                String sql = "select max(ingredient_id) from ingredient where ingredient_id < 1000";
                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();
                     //ResultSet rs = stmt.executeQuery("Select name from recipe where name like '%\" + key_word + \"%'");) {
                     ResultSet rs = stmt.executeQuery(sql);) {
                    while (rs.next()) {
                        ingredient_id = rs.getInt(1) + 1;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

//                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
//                     Statement stmt = conn.createStatement();) {
//                    try (ResultSet rsFood = stmt.executeQuery("insert into ingredient values(" + ingredient_id + "," + new_recipe_id +  "," + foodId + "," + quantity +",'" + unit + "')");) {
//                    } catch (SQLIntegrityConstraintViolationException ex) {
//                        ;
//                    }
//                    try (ResultSet rsFood = stmt.executeQuery("insert into food values(" + foodId + ",'" + foodName + "')");) {
//                    } catch (SQLIntegrityConstraintViolationException ex) {
//                        ;
//                    }
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }

                System.out.println("ingredient_id: " + ingredient_id);
                System.out.println("new_recipe_id: " + new_recipe_id);
                System.out.println("food_id: " + foodId);
                System.out.println("quantity: " + quantity);
                System.out.println("unit: " + unit);

//                    try (ResultSet rsFood = stmt.executeQuery("insert into ingredient values(" + ingredient_id + "," + new_recipe_id +  "," + foodId + "," + quantity +",'" + unit + "')");) {
//                    } catch (SQLIntegrityConstraintViolationException ex) {
//                        System.out.println("not added");
                //   }
                //  try (ResultSet rsList = stmt.executeQuery("insert into shopping_list values(" + foodId + ", 1)")) {

//

                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();) {
                    try (ResultSet rsFood = stmt.executeQuery("insert into ingredient values(" + ingredient_id + "," + new_recipe_id + "," + foodId + "," + quantity + ",'" + unit + "')");) {
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        System.out.println("not added to ingredient");
                        System.out.println(ex.getMessage());

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                System.out.println("added");

            }
        });
        enterStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String step = stepField.getText();
                int step_id = 0;

                String sqlQuery = "select max(step_id) from recipe_step where step_id < 1000";
                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();
                     //ResultSet rs = stmt.executeQuery("Select name from recipe where name like '%\" + key_word + \"%'");) {
                     ResultSet rs = stmt.executeQuery(sqlQuery);) {
                    while (rs.next()) {
                        step_id = rs.getInt(1) + 1;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();) {
                    try (ResultSet rsFood = stmt.executeQuery("insert into recipe_step values(" + step_id + "," + new_step_number + ",'" + step + "'," + new_recipe_id + ")");) {
                        step_id += 1;
                        new_step_number += 1;
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        ;
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
        newRecipeMainFrame = new JPanel();
        newRecipeMainFrame.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        newRecipeMainFrame.setBackground(new Color(-15946596));
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 16, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBackground(new Color(-15946596));
        panel.setEnabled(false);
        newRecipeMainFrame.add(panel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-15946596));
        panel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        quantityField = new JTextField();
        quantityField.setBackground(new Color(-16100280));
        quantityField.setDisabledTextColor(new Color(-1));
        Font quantityFieldFont = this.$$$getFont$$$("Goudy Old Style", Font.PLAIN, 20, quantityField.getFont());
        if (quantityFieldFont != null) quantityField.setFont(quantityFieldFont);
        quantityField.setForeground(new Color(-1));
        quantityField.setHorizontalAlignment(0);
        quantityField.setText("1");
        panel1.add(quantityField, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        unitField = new JTextField();
        unitField.setBackground(new Color(-16100280));
        unitField.setDisabledTextColor(new Color(-1));
        Font unitFieldFont = this.$$$getFont$$$("Goudy Old Style", Font.PLAIN, 20, unitField.getFont());
        if (unitFieldFont != null) unitField.setFont(unitFieldFont);
        unitField.setForeground(new Color(-1));
        unitField.setHorizontalAlignment(0);
        unitField.setText("glass");
        panel1.add(unitField, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-15946596));
        panel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(5, 15, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ingredientField = new JTextField();
        ingredientField.setBackground(new Color(-16100280));
        ingredientField.setDisabledTextColor(new Color(-1));
        Font ingredientFieldFont = this.$$$getFont$$$("Goudy Old Style", Font.PLAIN, 20, ingredientField.getFont());
        if (ingredientFieldFont != null) ingredientField.setFont(ingredientFieldFont);
        ingredientField.setForeground(new Color(-1));
        ingredientField.setHorizontalAlignment(0);
        ingredientField.setText("milk");
        panel.add(ingredientField, new com.intellij.uiDesigner.core.GridConstraints(5, 5, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        stepField = new JTextField();
        stepField.setBackground(new Color(-16100280));
        stepField.setDisabledTextColor(new Color(-1));
        Font stepFieldFont = this.$$$getFont$$$("Goudy Old Style", Font.PLAIN, 20, stepField.getFont());
        if (stepFieldFont != null) stepField.setFont(stepFieldFont);
        stepField.setForeground(new Color(-1));
        stepField.setHorizontalAlignment(0);
        stepField.setText("Add milk");
        panel.add(stepField, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 28, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-1));
        label1.setHorizontalAlignment(0);
        label1.setText("Enter steps");
        panel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 12, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tittle = new JLabel();
        tittle.setEnabled(true);
        Font tittleFont = this.$$$getFont$$$("Broadway", -1, 36, tittle.getFont());
        if (tittleFont != null) tittle.setFont(tittleFont);
        tittle.setForeground(new Color(-15946596));
        tittle.setHorizontalAlignment(0);
        tittle.setHorizontalTextPosition(0);
        tittle.setText("Create new recipe");
        panel.add(tittle, new com.intellij.uiDesigner.core.GridConstraints(1, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 28, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-1));
        label2.setHorizontalAlignment(0);
        label2.setText("Enter title");
        panel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 12, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 28, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-1));
        label3.setHorizontalAlignment(0);
        label3.setText("Enter ingredients");
        panel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 13, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(3, 8, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        enterTitleButton = new JButton();
        enterTitleButton.setBackground(new Color(-9906520));
        Font enterTitleButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 20, enterTitleButton.getFont());
        if (enterTitleButtonFont != null) enterTitleButton.setFont(enterTitleButtonFont);
        enterTitleButton.setForeground(new Color(-1));
        enterTitleButton.setText("Enter title");
        panel.add(enterTitleButton, new com.intellij.uiDesigner.core.GridConstraints(3, 10, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enterIngredientButton = new JButton();
        enterIngredientButton.setBackground(new Color(-9906520));
        Font enterIngredientButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 20, enterIngredientButton.getFont());
        if (enterIngredientButtonFont != null) enterIngredientButton.setFont(enterIngredientButtonFont);
        enterIngredientButton.setForeground(new Color(-1));
        enterIngredientButton.setText("Enter ingredient");
        panel.add(enterIngredientButton, new com.intellij.uiDesigner.core.GridConstraints(5, 10, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(5, 8, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(7, 8, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        enterStepButton = new JButton();
        enterStepButton.setBackground(new Color(-9906520));
        Font enterStepButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 20, enterStepButton.getFont());
        if (enterStepButtonFont != null) enterStepButton.setFont(enterStepButtonFont);
        enterStepButton.setForeground(new Color(-1));
        enterStepButton.setText("Enter step");
        panel.add(enterStepButton, new com.intellij.uiDesigner.core.GridConstraints(7, 10, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        titlePanel = new JPanel();
        titlePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        titlePanel.setBackground(new Color(-5570596));
        panel.add(titlePanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 16, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setEnabled(true);
        Font label4Font = this.$$$getFont$$$("Broadway", -1, 36, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-15946596));
        label4.setHorizontalAlignment(0);
        label4.setHorizontalTextPosition(0);
        label4.setText("Create new recipe");
        titlePanel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(8, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        titleField = new JTextField();
        titleField.setBackground(new Color(-16100280));
        titleField.setDisabledTextColor(new Color(-15953792));
        Font titleFieldFont = this.$$$getFont$$$("Goudy Old Style", Font.PLAIN, 20, titleField.getFont());
        if (titleFieldFont != null) titleField.setFont(titleFieldFont);
        titleField.setForeground(new Color(-1));
        titleField.setHorizontalAlignment(0);
        titleField.setText("Title");
        panel.add(titleField, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        newRecipeMainFrame.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        newRecipeMainFrame.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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
        return newRecipeMainFrame;
    }

    ;

}
