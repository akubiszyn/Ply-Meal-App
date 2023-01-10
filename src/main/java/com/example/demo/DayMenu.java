package com.example.demo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;

public class DayMenu extends JFrame {
    private JButton SupperAddButton;
    private JButton DessertAddButton;
    private JButton SnackAddButton;
    private JButton IIBreakfastAddButton;
    private JButton DinnerAddButton;
    private JPanel mainDayMenuFrame;
    private JLabel DayName;
    private JList IIBreakfastList;
    private JList DinnerList;
    private JList DessertList;
    private JList SupperList;
    private JList SnackList;
    private JPanel shoppingListTitlePanel;
    private JPanel DayMenuPanel;
    private JButton BreakfastAddButton;
    private JButton BreakfastRemoveButton;
    private JButton IIBreakfastRemoveButton;
    private JButton DinnerRemoveButton;
    private JButton DessertRemoveButton;
    private JButton SupperRemoveButton;
    private JButton SnackRemoveButton;
    private JList BreakfastList;

    private ArrayList<JList> mealLists = new ArrayList<>();


    public DayMenu(String day_name, int client_id) {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        DayName.setText(day_name);
        ArrayList<String> dayNames = new ArrayList<>(Arrays.asList("Monday",
                "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        this.setContentPane(mainDayMenuFrame);
        mealLists.add(BreakfastList);
        mealLists.add(IIBreakfastList);
        mealLists.add(DinnerList);
        mealLists.add(DessertList);
        mealLists.add(SupperList);
        mealLists.add(SnackList);
        for (JList mealList : mealLists) {
            show_meals(mealList, client_id, dayNames.indexOf(day_name) + 1, mealLists.indexOf(mealList) + 1);
        }


        ArrayList<JButton> addButtons = new ArrayList<>(Arrays.asList(
                BreakfastAddButton, IIBreakfastAddButton,
                DinnerAddButton, DessertAddButton,
                SupperAddButton, SnackAddButton));

        ArrayList<JButton> removeButtons = new ArrayList<>(Arrays.asList(
                BreakfastRemoveButton, IIBreakfastRemoveButton,
                DinnerRemoveButton, DessertRemoveButton,
                SupperRemoveButton, SnackRemoveButton
        ));
        int day_number = dayNames.indexOf(day_name) + 1;
        ActionListener removeButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object button = e.getSource();
                JList selectedList;
                int indexList = 0;
                if (button == BreakfastRemoveButton) {
                    selectedList = BreakfastList;
                    indexList = mealLists.indexOf(BreakfastList);
                } else if (button == IIBreakfastRemoveButton) {
                    selectedList = IIBreakfastList;
                    indexList = mealLists.indexOf(IIBreakfastList);
                } else if (button == DinnerRemoveButton) {
                    selectedList = DinnerList;
                    indexList = mealLists.indexOf(DinnerList);
                } else if (button == DessertRemoveButton) {
                    selectedList = DessertList;
                    indexList = mealLists.indexOf(DessertList);
                } else if (button == SupperRemoveButton) {
                    selectedList = SupperList;
                    indexList = mealLists.indexOf(SupperList);
                } else {
                    selectedList = SnackList;
                    indexList = mealLists.indexOf(SnackList);
                }
                int selectedItem = selectedList.getSelectedIndex();
                if (selectedItem != -1) {

                    try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("Delete from weekly_menu where recipe_id = (select recipe_id from recipe where name like '"
                                 + selectedList.getModel().getElementAt(selectedItem) + "' and client_id = " + client_id
                                 + " and meal_number = " + (indexList + 1) + " and day_number = " + day_number + ")");) {
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    show_meals(selectedList, client_id, day_number, mealLists.indexOf(selectedList) + 1);
                }
            }
        };

        ActionListener addButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object button = e.getSource();
                int meal_number = -1;
                if (button == BreakfastAddButton) {
                    meal_number = addButtons.indexOf(BreakfastAddButton);
                } else if (button == IIBreakfastAddButton) {
                    meal_number = addButtons.indexOf(IIBreakfastAddButton);
                } else if (button == DinnerAddButton) {
                    meal_number = addButtons.indexOf(DinnerAddButton);
                } else if (button == DessertAddButton) {
                    meal_number = addButtons.indexOf(DessertAddButton);
                } else if (button == SupperAddButton) {
                    meal_number = addButtons.indexOf(SupperAddButton);
                } else {
                    meal_number = addButtons.indexOf(SnackAddButton);
                }

                new SearchRecipe(client_id, 1, dayNames.indexOf(day_name) + 1, meal_number + 1);
                show_meals(mealLists.get(meal_number), client_id, dayNames.indexOf(day_name) + 1, addButtons.indexOf(button) + 1);
            }
        };
        BreakfastAddButton.addActionListener(addButtonListener);
        IIBreakfastAddButton.addActionListener(addButtonListener);
        DinnerAddButton.addActionListener(addButtonListener);
        DessertAddButton.addActionListener(addButtonListener);
        SupperAddButton.addActionListener(addButtonListener);
        SnackAddButton.addActionListener(addButtonListener);

        BreakfastRemoveButton.addActionListener(removeButtonListener);
        IIBreakfastRemoveButton.addActionListener(removeButtonListener);
        DinnerRemoveButton.addActionListener(removeButtonListener);
        DessertRemoveButton.addActionListener(removeButtonListener);
        SupperRemoveButton.addActionListener(removeButtonListener);
        SnackRemoveButton.addActionListener(removeButtonListener);
        this.setVisible(true);
    }

    public void show_meals(JList mealList, int client_id, int day_number, int meal_number) {
        DefaultListModel listModel = new DefaultListModel<>();
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select name from recipe where recipe_id in (select recipe_id from weekly_menu where client_id="
                     + client_id + " and day_number = " + day_number + " and meal_number = " + meal_number + ")");) {
            while (rs.next()) {
                listModel.addElement(rs.getString(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        mealList.setModel(listModel);
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
        mainDayMenuFrame = new JPanel();
        mainDayMenuFrame.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 8, new Insets(20, 20, 20, 20), -1, -1));
        mainDayMenuFrame.setBackground(new Color(-15946596));
        SupperAddButton = new JButton();
        SupperAddButton.setBackground(new Color(-9906520));
        Font SupperAddButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, SupperAddButton.getFont());
        if (SupperAddButtonFont != null) SupperAddButton.setFont(SupperAddButtonFont);
        SupperAddButton.setForeground(new Color(-3340));
        SupperAddButton.setMargin(new Insets(5, 5, 5, 5));
        SupperAddButton.setText("Add");
        mainDayMenuFrame.add(SupperAddButton, new com.intellij.uiDesigner.core.GridConstraints(8, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, 35), null, 0, false));
        DessertAddButton = new JButton();
        DessertAddButton.setBackground(new Color(-9906520));
        Font DessertAddButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, DessertAddButton.getFont());
        if (DessertAddButtonFont != null) DessertAddButton.setFont(DessertAddButtonFont);
        DessertAddButton.setForeground(new Color(-3340));
        DessertAddButton.setMargin(new Insets(5, 5, 5, 5));
        DessertAddButton.setText("Add");
        mainDayMenuFrame.add(DessertAddButton, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SnackAddButton = new JButton();
        SnackAddButton.setBackground(new Color(-9906520));
        Font SnackAddButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, SnackAddButton.getFont());
        if (SnackAddButtonFont != null) SnackAddButton.setFont(SnackAddButtonFont);
        SnackAddButton.setForeground(new Color(-3340));
        SnackAddButton.setMargin(new Insets(5, 5, 5, 5));
        SnackAddButton.setText("Add");
        mainDayMenuFrame.add(SnackAddButton, new com.intellij.uiDesigner.core.GridConstraints(8, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        IIBreakfastAddButton = new JButton();
        IIBreakfastAddButton.setBackground(new Color(-9906520));
        Font IIBreakfastAddButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, IIBreakfastAddButton.getFont());
        if (IIBreakfastAddButtonFont != null) IIBreakfastAddButton.setFont(IIBreakfastAddButtonFont);
        IIBreakfastAddButton.setForeground(new Color(-3340));
        IIBreakfastAddButton.setMargin(new Insets(5, 5, 5, 5));
        IIBreakfastAddButton.setText("Add");
        mainDayMenuFrame.add(IIBreakfastAddButton, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DinnerAddButton = new JButton();
        DinnerAddButton.setBackground(new Color(-9906520));
        Font DinnerAddButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, DinnerAddButton.getFont());
        if (DinnerAddButtonFont != null) DinnerAddButton.setFont(DinnerAddButtonFont);
        DinnerAddButton.setForeground(new Color(-3340));
        DinnerAddButton.setMargin(new Insets(5, 5, 5, 5));
        DinnerAddButton.setText("Add");
        mainDayMenuFrame.add(DinnerAddButton, new com.intellij.uiDesigner.core.GridConstraints(4, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BreakfastAddButton = new JButton();
        BreakfastAddButton.setBackground(new Color(-9906520));
        Font BreakfastAddButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, BreakfastAddButton.getFont());
        if (BreakfastAddButtonFont != null) BreakfastAddButton.setFont(BreakfastAddButtonFont);
        BreakfastAddButton.setForeground(new Color(-3340));
        BreakfastAddButton.setMargin(new Insets(5, 5, 5, 5));
        BreakfastAddButton.setText("Add");
        mainDayMenuFrame.add(BreakfastAddButton, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainDayMenuFrame.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(7, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        mainDayMenuFrame.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        mainDayMenuFrame.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        mainDayMenuFrame.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(3, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        BreakfastRemoveButton = new JButton();
        BreakfastRemoveButton.setBackground(new Color(-9906520));
        Font BreakfastRemoveButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, BreakfastRemoveButton.getFont());
        if (BreakfastRemoveButtonFont != null) BreakfastRemoveButton.setFont(BreakfastRemoveButtonFont);
        BreakfastRemoveButton.setForeground(new Color(-3340));
        BreakfastRemoveButton.setMargin(new Insets(5, 5, 5, 5));
        BreakfastRemoveButton.setText("Remove");
        mainDayMenuFrame.add(BreakfastRemoveButton, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        IIBreakfastRemoveButton = new JButton();
        IIBreakfastRemoveButton.setBackground(new Color(-9906520));
        Font IIBreakfastRemoveButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, IIBreakfastRemoveButton.getFont());
        if (IIBreakfastRemoveButtonFont != null) IIBreakfastRemoveButton.setFont(IIBreakfastRemoveButtonFont);
        IIBreakfastRemoveButton.setForeground(new Color(-3340));
        IIBreakfastRemoveButton.setMargin(new Insets(5, 5, 5, 5));
        IIBreakfastRemoveButton.setText("Remove");
        mainDayMenuFrame.add(IIBreakfastRemoveButton, new com.intellij.uiDesigner.core.GridConstraints(4, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DinnerRemoveButton = new JButton();
        DinnerRemoveButton.setBackground(new Color(-9906520));
        Font DinnerRemoveButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, DinnerRemoveButton.getFont());
        if (DinnerRemoveButtonFont != null) DinnerRemoveButton.setFont(DinnerRemoveButtonFont);
        DinnerRemoveButton.setForeground(new Color(-3340));
        DinnerRemoveButton.setMargin(new Insets(5, 5, 5, 5));
        DinnerRemoveButton.setText("Remove");
        mainDayMenuFrame.add(DinnerRemoveButton, new com.intellij.uiDesigner.core.GridConstraints(4, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DessertRemoveButton = new JButton();
        DessertRemoveButton.setBackground(new Color(-9906520));
        Font DessertRemoveButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, DessertRemoveButton.getFont());
        if (DessertRemoveButtonFont != null) DessertRemoveButton.setFont(DessertRemoveButtonFont);
        DessertRemoveButton.setForeground(new Color(-3340));
        DessertRemoveButton.setMargin(new Insets(5, 5, 5, 5));
        DessertRemoveButton.setText("Remove");
        mainDayMenuFrame.add(DessertRemoveButton, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, 35), null, 0, false));
        SupperRemoveButton = new JButton();
        SupperRemoveButton.setBackground(new Color(-9906520));
        Font SupperRemoveButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, SupperRemoveButton.getFont());
        if (SupperRemoveButtonFont != null) SupperRemoveButton.setFont(SupperRemoveButtonFont);
        SupperRemoveButton.setForeground(new Color(-3340));
        SupperRemoveButton.setMargin(new Insets(5, 5, 5, 5));
        SupperRemoveButton.setText("Remove");
        mainDayMenuFrame.add(SupperRemoveButton, new com.intellij.uiDesigner.core.GridConstraints(8, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, 35), null, 0, false));
        SnackRemoveButton = new JButton();
        SnackRemoveButton.setBackground(new Color(-9906520));
        Font SnackRemoveButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, SnackRemoveButton.getFont());
        if (SnackRemoveButtonFont != null) SnackRemoveButton.setFont(SnackRemoveButtonFont);
        SnackRemoveButton.setForeground(new Color(-3340));
        SnackRemoveButton.setMargin(new Insets(5, 5, 5, 5));
        SnackRemoveButton.setText("Remove");
        mainDayMenuFrame.add(SnackRemoveButton, new com.intellij.uiDesigner.core.GridConstraints(8, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-5570596));
        mainDayMenuFrame.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setEnabled(true);
        Font label1Font = this.$$$getFont$$$("Broadway", -1, 36, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-15946596));
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("Breakfast");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-5570596));
        mainDayMenuFrame.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setEnabled(true);
        Font label2Font = this.$$$getFont$$$("Broadway", -1, 36, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-15946596));
        label2.setHorizontalAlignment(0);
        label2.setHorizontalTextPosition(0);
        label2.setText("II Breakfast");
        panel2.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-5570596));
        mainDayMenuFrame.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(2, 6, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setEnabled(true);
        Font label3Font = this.$$$getFont$$$("Broadway", -1, 36, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-15946596));
        label3.setHorizontalAlignment(0);
        label3.setHorizontalTextPosition(0);
        label3.setText("Dinner");
        panel3.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-5570596));
        mainDayMenuFrame.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setEnabled(true);
        Font label4Font = this.$$$getFont$$$("Broadway", -1, 36, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-15946596));
        label4.setHorizontalAlignment(0);
        label4.setHorizontalTextPosition(0);
        label4.setText("Dessert");
        panel4.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setBackground(new Color(-5570596));
        mainDayMenuFrame.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setEnabled(true);
        Font label5Font = this.$$$getFont$$$("Broadway", -1, 36, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-15946596));
        label5.setHorizontalAlignment(0);
        label5.setHorizontalTextPosition(0);
        label5.setText("Supper");
        panel5.add(label5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setBackground(new Color(-5570596));
        mainDayMenuFrame.add(panel6, new com.intellij.uiDesigner.core.GridConstraints(6, 6, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setEnabled(true);
        Font label6Font = this.$$$getFont$$$("Broadway", -1, 36, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-15946596));
        label6.setHorizontalAlignment(0);
        label6.setHorizontalTextPosition(0);
        label6.setText("Snack");
        panel6.add(label6, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        mainDayMenuFrame.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(5, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        DayMenuPanel = new JPanel();
        DayMenuPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        DayMenuPanel.setBackground(new Color(-5570596));
        mainDayMenuFrame.add(DayMenuPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 8, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        DayName = new JLabel();
        DayName.setEnabled(true);
        Font DayNameFont = this.$$$getFont$$$("Broadway", -1, 36, DayName.getFont());
        if (DayNameFont != null) DayName.setFont(DayNameFont);
        DayName.setForeground(new Color(-15946596));
        DayName.setHorizontalAlignment(0);
        DayName.setHorizontalTextPosition(0);
        DayName.setText("");
        DayMenuPanel.add(DayName, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        mainDayMenuFrame.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        BreakfastList = new JList();
        BreakfastList.setBackground(new Color(-5570596));
        Font BreakfastListFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, -1, BreakfastList.getFont());
        if (BreakfastListFont != null) BreakfastList.setFont(BreakfastListFont);
        BreakfastList.setForeground(new Color(-16100280));
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        BreakfastList.setModel(defaultListModel1);
        BreakfastList.setSelectionBackground(new Color(-9118745));
        BreakfastList.setSelectionForeground(new Color(-16100280));
        mainDayMenuFrame.add(BreakfastList, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        IIBreakfastList = new JList();
        IIBreakfastList.setBackground(new Color(-5570596));
        Font IIBreakfastListFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, -1, IIBreakfastList.getFont());
        if (IIBreakfastListFont != null) IIBreakfastList.setFont(IIBreakfastListFont);
        IIBreakfastList.setForeground(new Color(-16100280));
        final DefaultListModel defaultListModel2 = new DefaultListModel();
        IIBreakfastList.setModel(defaultListModel2);
        IIBreakfastList.setSelectionBackground(new Color(-9118745));
        IIBreakfastList.setSelectionForeground(new Color(-16100280));
        mainDayMenuFrame.add(IIBreakfastList, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        DinnerList = new JList();
        DinnerList.setBackground(new Color(-5570596));
        Font DinnerListFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, -1, DinnerList.getFont());
        if (DinnerListFont != null) DinnerList.setFont(DinnerListFont);
        DinnerList.setForeground(new Color(-16100280));
        final DefaultListModel defaultListModel3 = new DefaultListModel();
        DinnerList.setModel(defaultListModel3);
        DinnerList.setSelectionBackground(new Color(-9118745));
        DinnerList.setSelectionForeground(new Color(-16100280));
        mainDayMenuFrame.add(DinnerList, new com.intellij.uiDesigner.core.GridConstraints(3, 6, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        SnackList = new JList();
        SnackList.setBackground(new Color(-5570596));
        Font SnackListFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, -1, SnackList.getFont());
        if (SnackListFont != null) SnackList.setFont(SnackListFont);
        SnackList.setForeground(new Color(-16100280));
        final DefaultListModel defaultListModel4 = new DefaultListModel();
        SnackList.setModel(defaultListModel4);
        SnackList.setSelectionBackground(new Color(-9118745));
        SnackList.setSelectionForeground(new Color(-16100280));
        mainDayMenuFrame.add(SnackList, new com.intellij.uiDesigner.core.GridConstraints(7, 6, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        SupperList = new JList();
        SupperList.setBackground(new Color(-5570596));
        Font SupperListFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, -1, SupperList.getFont());
        if (SupperListFont != null) SupperList.setFont(SupperListFont);
        SupperList.setForeground(new Color(-16100280));
        final DefaultListModel defaultListModel5 = new DefaultListModel();
        SupperList.setModel(defaultListModel5);
        SupperList.setSelectionBackground(new Color(-9118745));
        SupperList.setSelectionForeground(new Color(-16100280));
        mainDayMenuFrame.add(SupperList, new com.intellij.uiDesigner.core.GridConstraints(7, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        DessertList = new JList();
        DessertList.setBackground(new Color(-5570596));
        Font DessertListFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, -1, DessertList.getFont());
        if (DessertListFont != null) DessertList.setFont(DessertListFont);
        DessertList.setForeground(new Color(-16100280));
        final DefaultListModel defaultListModel6 = new DefaultListModel();
        DessertList.setModel(defaultListModel6);
        DessertList.setSelectionBackground(new Color(-9118745));
        DessertList.setSelectionForeground(new Color(-16100280));
        mainDayMenuFrame.add(DessertList, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
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
        return mainDayMenuFrame;
    }

}
