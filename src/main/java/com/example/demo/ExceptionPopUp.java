package com.example.demo;

import javax.swing.*;

public class ExceptionPopUp extends JFrame{
    private JPanel panel;
    private JLabel exceptionField;

    public ExceptionPopUp (String message) {
        super();
        this.setSize(300, 300);
        this.setBounds(600, 250, 300, 300);
        this.setContentPane(panel);
        this.setVisible(true);
        exceptionField.setText(message);
    }


}
