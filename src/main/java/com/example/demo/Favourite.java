package com.example.demo;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.sql.*;

public class Favourite extends JFrame {
    private JPanel fridgeTitlePanel;
    private JLabel tittle;
    private JList favouriteList;
    private JPanel panel;

    private int client_id;

    public Favourite(int client_id) {
        super();
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(panel);
        DefaultListModel listModel = new DefaultListModel<>();
        this.client_id = client_id;
        show_fav();
        this.setVisible(true);

        favouriteList.addListSelectionListener(new ListSelectionListener() {
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

    public void show_fav()
    {
        DefaultListModel model = new DefaultListModel();
        System.out.println("aaaa: " + client_id);
        String sqlQuery = "select name from recipe inner join fav_recipe using (recipe_id) where client_id ="+ client_id;
        System.out.println(sqlQuery);
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
        favouriteList.setModel(model);
    }
}
