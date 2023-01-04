package com.example.demo;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class SearchRecipe extends JFrame {
    private JPanel recipeTitlePanel;
    private JLabel tittle;
    private JPanel recipeMainFrame;
    private JButton searchRecipeButton;
    private JTextField enterRecipe;
    private JList recipeList;

    public SearchRecipe() {
        super();
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(recipeMainFrame);
        this.setVisible(true);

        searchRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pattern = enterRecipe.getText();
                DefaultListModel model = new DefaultListModel();
                model.addElement(pattern);
                model.addElement("aaaaa");
                recipeList.setModel(model);

            }
        });
    }

    ;

}
