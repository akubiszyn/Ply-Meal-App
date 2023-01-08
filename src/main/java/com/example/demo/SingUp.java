package com.example.demo;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Locale;

public class SingUp extends JFrame {
    private JTextField usernameTextField;
    private JPanel panel1;
    private JButton singUpButton;
    private JLabel usernameLabel;

    private int new_client_id;

    public SingUp() {
        super();
        this.setSize(300, 300);
        this.setBounds(600, 250, 300, 300);
        this.setContentPane(panel1);
        DefaultListModel listModel = new DefaultListModel<>();
        this.setVisible(true);

        String sqlQuery = "select max(client_id) from client where client_id < 1000";
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             //ResultSet rs = stmt.executeQuery("Select name from recipe where name like '%\" + key_word + \"%'");) {
             ResultSet rs = stmt.executeQuery(sqlQuery);) {
            while (rs.next()) {
                new_client_id = rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        singUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                System.out.println(username);

                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();) {
                    try (ResultSet rsClient = stmt.executeQuery("insert into client values(" + new_client_id + ",'" + username + "')");) {
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        System.out.println("client not added");
                        System.out.println(ex.getMessage());
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

}
