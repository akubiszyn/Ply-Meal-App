package com.example.demo;
import com.example.demo.Food.FoodController;
import com.example.demo.Food.ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
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

    public Fridge() {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(fridgeMainFrame);
        DefaultListModel listModel = new DefaultListModel<>();
        this.setVisible(true);
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pattern = enterItem.getText();
                FoodController foodController = new FoodController();
                Ingredient ingredient = foodController.getIngredient(pattern, "100", "gram");
                String foodName = ingredient.getName();
                String foodImage = ingredient.getImage();
                String sqlAddFood = "Insert into food values ('" + foodName + "', '" + foodImage + "');";
                System.out.println(foodName);
                System.out.println(foodImage);
                listModel.addElement(foodName);
//                jdbcTemplate.update(sqlAddFood);
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


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        fridgeMainFrame = new JPanel();
        fridgeMainFrame.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 21, new Insets(20, 20, 20, 20), -1, -1));
        fridgeMainFrame.setBackground(new Color(-15946596));
        fridgeTitlePanel = new JPanel();
        fridgeTitlePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        fridgeTitlePanel.setBackground(new Color(-5570596));
        fridgeMainFrame.add(fridgeTitlePanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 21, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        tittle = new JLabel();
        tittle.setEnabled(true);
        Font tittleFont = this.$$$getFont$$$("Broadway", -1, 36, tittle.getFont());
        if (tittleFont != null) tittle.setFont(tittleFont);
        tittle.setForeground(new Color(-15946596));
        tittle.setHorizontalAlignment(0);
        tittle.setHorizontalTextPosition(0);
        tittle.setText("What's in my fridge");
        fridgeTitlePanel.add(tittle, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        fridgeMainFrame.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 21, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        fridgeMainFrame.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(4, 20, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        fridgeMainFrame.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        itemList = new JList();
        itemList.setBackground(new Color(-5570596));
        itemList.setDoubleBuffered(true);
        Font itemListFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, itemList.getFont());
        if (itemListFont != null) itemList.setFont(itemListFont);
        itemList.setForeground(new Color(-16100280));
        itemList.setSelectionBackground(new Color(-9118745));
        itemList.setSelectionForeground(new Color(-16100280));
        fridgeMainFrame.add(itemList, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 2, 15, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        removeItemButton = new JButton();
        removeItemButton.setBackground(new Color(-9118745));
        Font removeItemButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, removeItemButton.getFont());
        if (removeItemButtonFont != null) removeItemButton.setFont(removeItemButtonFont);
        removeItemButton.setForeground(new Color(-16100280));
        removeItemButton.setMargin(new Insets(5, 5, 5, 5));
        removeItemButton.setText("Remove item");
        fridgeMainFrame.add(removeItemButton, new com.intellij.uiDesigner.core.GridConstraints(2, 6, 1, 9, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enterItem = new JTextField();
        enterItem.setBackground(new Color(-9118745));
        Font enterItemFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, enterItem.getFont());
        if (enterItemFont != null) enterItem.setFont(enterItemFont);
        enterItem.setForeground(new Color(-16100280));
        enterItem.setMargin(new Insets(2, 6, 2, 6));
        enterItem.setText("");
        fridgeMainFrame.add(enterItem, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(88, 39), null, 0, false));
        addItemButton = new JButton();
        addItemButton.setBackground(new Color(-9118745));
        Font addItemButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, addItemButton.getFont());
        if (addItemButtonFont != null) addItemButton.setFont(addItemButtonFont);
        addItemButton.setForeground(new Color(-16100280));
        addItemButton.setMargin(new Insets(5, 5, 5, 5));
        addItemButton.setText("Add item");
        fridgeMainFrame.add(addItemButton, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return fridgeMainFrame;
    }

}
