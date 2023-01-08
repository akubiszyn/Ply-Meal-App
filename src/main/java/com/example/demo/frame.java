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
    private JButton favouriteButton2;

    private LogIn login_window;

    public int client_id = 0;

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
                if(check_user())
                {
                    SearchRecipe search = new SearchRecipe(client_id);
                }
            }
        });

        searchNutritonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check_user()) {
                    SearchNutrition nutrition = new SearchNutrition();
                }

            }
        });

        weeklyMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check_user()) {
                    WeeklyMenu menu = new WeeklyMenu();
                }
            }
        });

        myFridgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check_user())
                {
                    Fridge fridge = new Fridge();
                }
            }
        });

        shoppingListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check_user()) {
                    ShoppingList shoppingList = new ShoppingList();
                }
            }
        });

        addRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check_user()) {
                    NewRecipe newRecipe = new NewRecipe();
                }
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
                login_window = logIn;
            }
        });
        favouriteButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check_user()) {
                    Favourite favourite = new Favourite(client_id);
                }
            }
        });

    }
    public boolean check_user()
    {
        try
        {
            client_id = login_window.client_id;
            if (client_id == 0)
            {
                System.out.println("You have to log in first");
                ExceptionPopUp exceptionPopUp = new ExceptionPopUp("You have to log in first!");
                return false;
            }
            else
            {
                System.out.println("User id: " + client_id);
                return true;
            }
        }
        catch(Exception e)
        {
            System.out.println("You have to log in first");
            ExceptionPopUp exceptionPopUp = new ExceptionPopUp("You have to log in first!");
            return false;
        }

    }

//    public void add_user(String new_username)
//    {
//        this. = new_username;
//    }

    //    public static void main(String[] args){
//
//        JFrame window = new frame();
//        SpringApplication.run(DemoApplication.class, args);
//    }

//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//    }
}
