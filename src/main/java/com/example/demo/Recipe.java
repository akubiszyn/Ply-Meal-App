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

    private int fav = 0;


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

        String sql = "select * from fav_recipe where recipe_id =" + recipe_id;
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            if (rs.next()) {
                fav = 1;
            }
//            while (rs.next()) {
//                model.addElement(rs.getString(1));
//            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        if (fav == 1) {
            favouriteButton.setText("Delete recipe from favourite recipes");
        } else {
            favouriteButton.setText("Add recipe to favourite recipes");
        }


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
        favouriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = favouriteButton.getText();
                int client_id = 1;

                if (message == "Add recipe to favourite recipes") {
                    try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                         Statement stmt = conn.createStatement();) {

                        try (ResultSet rsFood = stmt.executeQuery("insert into fav_recipe values(" + client_id + ", '" + recipe_id + "')");) {
                        } catch (SQLIntegrityConstraintViolationException ex) {
                            ;
                        }

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    favouriteButton.setText("Delete recipe from favourite recipes");
                    fav = 1;
                } else {
                    try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("Delete from fav_recipe where recipe_id =" + recipe_id);) {
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    favouriteButton.setText("Add recipe to favourite recipes");
                    fav = 0;
                }
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


}
