package com.example.demo;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Locale;

public class LogIn extends JFrame {
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JButton loginButton;
    private JPanel panel1;

    public int client_id = 0;

    private int correct_login = 0;

    public LogIn() {
        super();
        this.setSize(300, 300);
        this.setBounds(600, 250, 300, 300);
        this.setContentPane(panel1);
        DefaultListModel listModel = new DefaultListModel<>();
        this.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                System.out.println("username entered: " + username);

                String sql = "select client_id from client where name ='" + username + "'";
                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(sql);) {
                    if (rs.next()) {
                        correct_login = 1;
                        client_id = rs.getInt(1);
                        System.out.println("client_id found: " + client_id);
                    }
                    else
                    {
                        correct_login = 0;
                        System.out.println("User is not signed up");
                        ExceptionPopUp exceptionPopUp = new ExceptionPopUp("User not registered!");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

//                if (correct_login==1)
//                {
//                    client_username = username;
//                }
//                else
//                {
//                    System.out.println("User is not signed up");
//                }

            }
        });
    }

}
