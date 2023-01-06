package com.example.demo;
import com.example.demo.Food.FoodController;
import com.example.demo.Food.ingredient.Ingredient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.URL;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class Recipe extends JFrame {

    private JPanel panel;
    private JList ingredientsList;
    private JList stepsList;

    private int recipe_id;

    private String image_url;

    private JButton shoppingButton;
    private JButton favouriteButton;
    private JLabel recipeTitle;
    private JLabel imageLabel;


    public Recipe(String selected) {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(panel);
        //DefaultListModel listModel = new DefaultListModel<>();
        this.recipeTitle.setText(selected);

        DefaultListModel model = new DefaultListModel();
        String sqlQuery = "select recipe_id, image_url from recipe where name like '%" + selected + "%'";
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             //ResultSet rs = stmt.executeQuery("Select name from recipe where name like '%\" + key_word + \"%'");) {
             ResultSet rs = stmt.executeQuery(sqlQuery);) {
            while (rs.next()) {
                this.recipe_id = rs.getInt(1);
                this.image_url = rs.getString(2);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        ArrayList<ArrayList<String>> ingredients = get_ingredients(this.recipe_id);
        show_ingredients(ingredients);
        //get_ingredients(Integer.toString(this.recipe_id));

        get_steps(this.recipe_id);

        Image image = null;
        try {
            URL url = new URL(image_url);
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageLabel.setIcon(new ImageIcon(image));
        this.setVisible(true);

        shoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();) {
                    for (ArrayList<String> ingredient : ingredients) {
                        String foodName = ingredient.get(2);
                        String foodId = ingredient.get(3);
                        try (ResultSet rsFood = stmt.executeQuery("insert into food values(" + foodId + ", '" + foodName + "')");) {
                        } catch (SQLIntegrityConstraintViolationException ex) {
                            try (ResultSet rsList = stmt.executeQuery("insert into shopping_list values(" + foodId + ", 1)")) {
                            } catch (SQLIntegrityConstraintViolationException exc) {
                                ;
                            }
                        }
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

//                model.addElement("aaaaa");
            }
        });
    }


    public ArrayList<ArrayList<String>> get_ingredients(int key_word) {
//        DefaultListModel model = new DefaultListModel();
        ArrayList<ArrayList<String>> ingredients = new ArrayList<>();
        String result, quantity, unit, name, id;
        String sqlQuery = "SELECT i.amount, i.unit, f.name, item_id\n" +
                "FROM food f INNER JOIN ingredient i USING (item_id)\n" +
                "WHERE i.recipe_id = " + key_word;
        //'"+ key_word +"'
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             //ResultSet rs = stmt.executeQuery("Select name from recipe where name like '%\" + key_word + \"%'");) {
             ResultSet rs = stmt.executeQuery(sqlQuery);) {
            while (rs.next()) {
                ArrayList<String> ingredient = new ArrayList<>();
                quantity = rs.getString(1);
                unit = rs.getString(2);
                if (unit == null) {
                    unit = "";
                }
                System.out.println(unit);
                name = rs.getString(3);
                id = rs.getString(4);
                ingredient.add(quantity);
                ingredient.add(unit);
                ingredient.add(name);
                ingredient.add(id);
//                result = quantity + " " + unit + " " + name;
//                model.addElement(result);
                ingredients.add(ingredient);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
//        ingredientsList.setModel(model);
        return ingredients;
    }

    public void show_ingredients(ArrayList<ArrayList<String>> ingredients) {
        DefaultListModel model = new DefaultListModel();
        for (ArrayList<String> ingredient : ingredients) {
            String result;
            result = ingredient.get(0) + " " + ingredient.get(1) + " " + ingredient.get(2);
            model.addElement(result);
        }
        ingredientsList.setModel(model);
    }

    public void get_steps(int key_word) {
        DefaultListModel model = new DefaultListModel();
        String result, step_number, step;
        String sqlQuery = "SELECT step_number, description FROM recipe_step WHERE recipe_id = " + key_word + "ORDER BY step_number";
        //'"+ key_word +"'
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             //ResultSet rs = stmt.executeQuery("Select name from recipe where name like '%\" + key_word + \"%'");) {
             ResultSet rs = stmt.executeQuery(sqlQuery);) {
            while (rs.next()) {
                step_number = rs.getString(1);
                step = rs.getString(2);
                if (step.length() > 200) {
                    String new_step, part1, part2, part3, part4;
////                    String[] parts = step.split("(?=\\D)", 2);
////                    step = parts[0] + "wwww" + parts[1];
                    part1 = step.substring(0, step.length() / 4);
                    System.out.println(part1);
                    part2 = step.substring(step.length() / 4 + 1, step.length() / 4 * 2);
                    System.out.println(part2);
                    part3 = step.substring(step.length() / 4 * 2 + 1, step.length() / 4 * 3);
                    System.out.println(part1);
                    part4 = step.substring(step.length() / 4 * 3 + 1);
                    System.out.println(part2);
//                    step =  part1 + "wwww" + part2;
                    result = step_number + " " + part1;
                    model.addElement(result);
                    result = "  " + part2;
                    model.addElement(result);
                    result = "  " + part3;
                    model.addElement(result);
                    step_number = "  ";
                    step = part4;
                } else if (step.length() > 100) {
                    String new_step, part1, part2;
////                    String[] parts = step.split("(?=\\D)", 2);
////                    step = parts[0] + "wwww" + parts[1];
                    part1 = step.substring(0, step.length() / 2);
                    System.out.println(part1);
                    part2 = step.substring(step.length() / 2);
                    System.out.println(part2);
//                    step =  part1 + "wwww" + part2;
                    result = step_number + " " + part1;
                    model.addElement(result);
                    step_number = "  ";
                    step = part2;
                }

                result = step_number + " " + step;
                model.addElement(result);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        stepsList.setModel(model);
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
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 5, new Insets(10, 10, 10, 10), -1, -1));
        panel.setBackground(new Color(-15946596));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        recipeTitle = new JLabel();
        Font recipeTitleFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, recipeTitle.getFont());
        if (recipeTitleFont != null) recipeTitle.setFont(recipeTitleFont);
        recipeTitle.setForeground(new Color(-3340));
        recipeTitle.setText("Label");
        panel.add(recipeTitle, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ingredientsList = new JList();
        ingredientsList.setBackground(new Color(-5570596));
        Font ingredientsListFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 14, ingredientsList.getFont());
        if (ingredientsListFont != null) ingredientsList.setFont(ingredientsListFont);
        ingredientsList.setForeground(new Color(-16100280));
        panel.add(ingredientsList, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        stepsList = new JList();
        stepsList.setBackground(new Color(-5570596));
        Font stepsListFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 14, stepsList.getFont());
        if (stepsListFont != null) stepsList.setFont(stepsListFont);
        stepsList.setForeground(new Color(-16100280));
        panel.add(stepsList, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        shoppingButton = new JButton();
        shoppingButton.setBackground(new Color(-9900609));
        Font shoppingButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 14, shoppingButton.getFont());
        if (shoppingButtonFont != null) shoppingButton.setFont(shoppingButtonFont);
        shoppingButton.setForeground(new Color(-16100280));
        shoppingButton.setText("Add ingredients to shopping list");
        panel.add(shoppingButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        favouriteButton = new JButton();
        favouriteButton.setBackground(new Color(-9900609));
        Font favouriteButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 14, favouriteButton.getFont());
        if (favouriteButtonFont != null) favouriteButton.setFont(favouriteButtonFont);
        favouriteButton.setForeground(new Color(-16100280));
        favouriteButton.setText("Add recipe to favourite recipes");
        panel.add(favouriteButton, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        imageLabel = new JLabel();
        imageLabel.setText(" ");
        panel.add(imageLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return panel;
    }

}
