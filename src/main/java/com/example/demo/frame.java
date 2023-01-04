package com.example.demo;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class frame extends JFrame {

    private JPanel mainFrame;
    private JPanel titlePanel;
    private JLabel tittle;
    private JButton shoppingListButton;
    private JButton weeklyMenuButton;
    private JButton myFridgeButton;
    private JButton searchNutritonButton;
    private JButton searchRecipeButton;
    private JButton addRecipeButton;
    private JButton logInButton;
    private JButton signUpButton;

    public frame() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(mainFrame);
        this.setVisible(true);

        searchRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchRecipe search = new SearchRecipe();
            }
        });

        searchNutritonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchNutrition nutrition = new SearchNutrition();

            }
        });

        weeklyMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WeeklyMenu menu = new WeeklyMenu();
            }
        });

        myFridgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fridge fridge = new Fridge();
            }
        });

        shoppingListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShoppingList shoppingList = new ShoppingList();
            }
        });

        addRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewRecipe newRecipe = new NewRecipe();
            }

        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SingUp singUp = new SingUp();
            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogIn logIn = new LogIn();
            }
        });
    }

    //    public static void main(String[] args){
//
//        JFrame window = new frame();
//        SpringApplication.run(DemoApplication.class, args);
//    }

//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//    }
}
