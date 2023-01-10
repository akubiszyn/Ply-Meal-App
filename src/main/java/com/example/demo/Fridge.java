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
    private JButton recommendedRecipe;
    private DefaultListModel listModel;
    private int clientId;

    public Fridge(int client_id) {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.clientId = client_id;
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(fridgeMainFrame);
        DefaultListModel listModel = new DefaultListModel<>();
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select name from fridge_list join food using (item_id) where client_id = " + this.clientId);) {
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
                int itemId = -1;
                String pattern = enterItem.getText();
                String foodName = pattern;
                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();
                     ResultSet rs1 = stmt.executeQuery("select item_id from food where name like '%" + pattern + "%'");) {
                    if (rs1.next()) {
                        itemId = rs1.getInt(1);
                    } else {
                        FoodController foodController = new FoodController();
                        Ingredient ingredient = foodController.getIngredient(pattern, "100", "gram");
                        foodName = ingredient.getName();
                        itemId = ingredient.getId();
                        try (ResultSet insertFood = stmt.executeQuery("insert into food values(" + itemId + ", '" + foodName + "')");) {
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();
                     ResultSet rs2 = stmt.executeQuery("insert into fridge_list columns (item_id, client_id) values (" + itemId + ", " + clientId + ")");) {
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
                         ResultSet rs = stmt.executeQuery("Delete from fridge_list where item_id = (select item_id from food where name like '" + listModel.getElementAt(selectedItem) + "') and client_id = " + clientId);) {
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    listModel.remove(selectedItem);
                }
                itemList.setModel(listModel);
            }
        });
        recommendedRecipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("select count(*) as ilosc, r.name from recipe r join ingredient i using(recipe_id) right join fridge_list f using (item_id) group by r.name order by ilosc desc");) {
                    if (rs.next()) {
                        String recipe_name = rs.getString(2);
                        Recipe recipe = new Recipe(recipe_name, clientId);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

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
        fridgeMainFrame.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 23, new Insets(20, 20, 20, 20), -1, -1));
        fridgeMainFrame.setBackground(new Color(-15946596));
        fridgeTitlePanel = new JPanel();
        fridgeTitlePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        fridgeTitlePanel.setBackground(new Color(-5570596));
        fridgeMainFrame.add(fridgeTitlePanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 22, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
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
        fridgeMainFrame.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 22, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        fridgeMainFrame.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(4, 22, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        fridgeMainFrame.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        itemList = new JList();
        itemList.setBackground(new Color(-5570596));
        itemList.setDoubleBuffered(true);
        Font itemListFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, itemList.getFont());
        if (itemListFont != null) itemList.setFont(itemListFont);
        itemList.setForeground(new Color(-16100280));
        itemList.setSelectionBackground(new Color(-9118745));
        itemList.setSelectionForeground(new Color(-16100280));
        fridgeMainFrame.add(itemList, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 2, 16, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        removeItemButton = new JButton();
        removeItemButton.setBackground(new Color(-9118745));
        Font removeItemButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, removeItemButton.getFont());
        if (removeItemButtonFont != null) removeItemButton.setFont(removeItemButtonFont);
        removeItemButton.setForeground(new Color(-16100280));
        removeItemButton.setMargin(new Insets(5, 5, 5, 5));
        removeItemButton.setText("Remove item");
        fridgeMainFrame.add(removeItemButton, new com.intellij.uiDesigner.core.GridConstraints(2, 8, 1, 9, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enterItem = new JTextField();
        enterItem.setBackground(new Color(-9118745));
        Font enterItemFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, enterItem.getFont());
        if (enterItemFont != null) enterItem.setFont(enterItemFont);
        enterItem.setForeground(new Color(-16100280));
        enterItem.setMargin(new Insets(2, 6, 2, 6));
        enterItem.setText("");
        fridgeMainFrame.add(enterItem, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(88, 39), null, 0, false));
        addItemButton = new JButton();
        addItemButton.setBackground(new Color(-9118745));
        Font addItemButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, addItemButton.getFont());
        if (addItemButtonFont != null) addItemButton.setFont(addItemButtonFont);
        addItemButton.setForeground(new Color(-16100280));
        addItemButton.setMargin(new Insets(5, 5, 5, 5));
        addItemButton.setText("Add item");
        fridgeMainFrame.add(addItemButton, new com.intellij.uiDesigner.core.GridConstraints(2, 5, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        fridgeMainFrame.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(5, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        recommendedRecipe = new JButton();
        recommendedRecipe.setBackground(new Color(-9118745));
        Font recommendedRecipeFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, recommendedRecipe.getFont());
        if (recommendedRecipeFont != null) recommendedRecipe.setFont(recommendedRecipeFont);
        recommendedRecipe.setForeground(new Color(-16100280));
        recommendedRecipe.setMargin(new Insets(5, 5, 5, 5));
        recommendedRecipe.setText("Recommended recipe");
        fridgeMainFrame.add(recommendedRecipe, new com.intellij.uiDesigner.core.GridConstraints(5, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        fridgeMainFrame.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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

    ;


}
