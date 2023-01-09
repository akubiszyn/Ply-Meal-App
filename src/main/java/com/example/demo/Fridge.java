package com.example.demo;
import com.example.demo.Food.FoodController;
import com.example.demo.Food.ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.sql.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.nimbus.State;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;


public class Fridge extends JFrame {
    private JPanel fridgeMainFrame;
    private JPanel fridgeTitlePanel;
    private JLabel tittle;
    private JList itemList;
    private JButton addItemButton;
    private JTextField enterItem;
    private JButton removeItemButton;
    private DefaultListModel listModel;
    private int clientId = 1;

    public Fridge() {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(fridgeMainFrame);
        DefaultListModel listModel = new DefaultListModel<>();
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select name from fridge_list join food using (item_id)");) {
            while (rs.next()) {
                listModel.addElement(rs.getString(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        itemList.setModel(listModel);
        this.setVisible(true);
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pattern = enterItem.getText();
                FoodController foodController = new FoodController();
                Ingredient ingredient = foodController.getIngredient(pattern, "100", "gram");
                String foodName = ingredient.getName();
                int foodId = ingredient.getId();

                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("insert into fridge_list columns (item_id, client_id) values (" + foodId + ", " + clientId + ")");) {
                    listModel.addElement(foodName);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                itemList.setModel(listModel);
                enterItem.setText("");
            }
        });
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedItem = itemList.getSelectedIndex();
                if (selectedItem != -1) {
                    try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("Delete from fridge_list where item_id = (select item_id from food where name like '" + listModel.getElementAt(selectedItem) + "')");) {
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    listModel.remove(selectedItem);
                }
                itemList.setModel(listModel);
            }
        });
    }

    ;


}
