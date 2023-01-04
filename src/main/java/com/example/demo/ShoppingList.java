package com.example.demo;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class ShoppingList extends JFrame {
    private JPanel shoppingListMainFrame;
    private JPanel shoppingListTitlePanel;
    private JLabel tittle;
    private JList itemList;
    private JButton removeItemButton;
    private JTextField enterItem;
    private JButton addItemButton;

    public ShoppingList() {
        super();
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(shoppingListMainFrame);
        DefaultListModel listModel = new DefaultListModel<>();
        this.setVisible(true);

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pattern = enterItem.getText();
                listModel.addElement(pattern);
//                model.addElement("aaaaa");
                itemList.setModel(listModel);
                enterItem.setText("");
            }
        });
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedItem = itemList.getSelectedIndex();
                if (selectedItem != -1) {
                    listModel.remove(selectedItem);
                }
                itemList.setModel(listModel);
            }
        });
    }

    ;

}
