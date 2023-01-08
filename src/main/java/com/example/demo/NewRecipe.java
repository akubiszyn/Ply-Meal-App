package com.example.demo;

import com.example.demo.Food.FoodController;
import com.example.demo.Food.ingredient.Ingredient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

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
        this.setVisible(true);

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

        enterTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();

                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();) {
                        try (ResultSet rsFood = stmt.executeQuery("insert into recipe values(" + new_recipe_id + ", '" + title +"'," + null + ")");) {
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

                int quantity =  Integer.parseInt(quantityField.getText());
                String unit = unitField.getText();
                String name = ingredientField.getText();
                int ingredient_id = 0;

                /*get from library*/
                FoodController foodController = new FoodController();
                Ingredient ingredient = foodController.getIngredient(name, "100", "gram");
                String foodName = ingredient.getName();
                int foodId = ingredient.getId();

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
                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();) {
                    try (ResultSet rsFood = stmt.executeQuery("insert into food values(" + foodId + ", '" + foodName + "')");) {
                    } catch (SQLIntegrityConstraintViolationException ex) {
//                        try (ResultSet rsList = stmt.executeQuery("insert into shopping_list values(" + foodId + ", 1)")) {
//                            listModel.addElement(foodName);
//                        } catch (SQLIntegrityConstraintViolationException exc) {
//                            ;
                        System.out.println("not added to food");
                        System.out.println(ex.getMessage());
//                        }
                        ;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                System.out.println("ingredient_id: "+ ingredient_id);
                System.out.println("new_recipe_id: "+ new_recipe_id);
                System.out.println("food_id: "+ foodId);
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
                    try (ResultSet rsFood = stmt.executeQuery("insert into ingredient values(" + ingredient_id + "," + new_recipe_id +  "," + foodId + "," + quantity +",'" + unit + "')");) {
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
                    try (ResultSet rsFood = stmt.executeQuery("insert into recipe_step values(" + step_id + "," + new_step_number +  ",'" + step + "'," + new_recipe_id + ")");) {
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

    ;

}
