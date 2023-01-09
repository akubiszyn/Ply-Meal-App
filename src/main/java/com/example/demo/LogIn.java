package com.example.demo;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class LogIn extends JFrame {
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JButton loginButton;
    private JPanel panel1;

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
                System.out.println(username);
            }
        });
    }

}
