package com.example.demo;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Locale;

public class SearchRecipe extends JFrame {
    private JPanel recipeTitlePanel;
    private JLabel tittle;
    private JPanel recipeMainFrame;
    private JButton searchRecipeButton;
    private JTextField enterRecipe;
    private JList recipeList;

    private int client_id;

    public SearchRecipe(int client_id) {
        super();
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(recipeMainFrame);
        this.setVisible(true);
        this.client_id = client_id;

        searchRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key_word = enterRecipe.getText();
                DefaultListModel model = new DefaultListModel();
//                model.addElement(key_word);
                String sqlQuery = "select name from recipe where name like '%" + key_word + "%'";
                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();
                     //ResultSet rs = stmt.executeQuery("Select name from recipe where name like '%\" + key_word + \"%'");) {
                     ResultSet rs = stmt.executeQuery(sqlQuery);) {
                    while (rs.next()) {
                        model.addElement(rs.getString(1));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                recipeList.setModel(model);

            }
        });
        recipeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    JList source = (JList) e.getSource();
                    String selected = source.getSelectedValue().toString();
                    System.out.println(selected);
                    Recipe recipe = new Recipe(selected, client_id);
                    //recipe.recipeTitle.setText(selected);
                }
            }
        });
    }

    ;

}
