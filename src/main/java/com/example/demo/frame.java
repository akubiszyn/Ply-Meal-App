package com.example.demo;

import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

@Component
public class frame extends JFrame {

    private JPanel mainFrame;
    private JPanel titlePanel;
    private JLabel tittle;
    private JButton shoppingListButton;
    private JButton weeklyMenuButton;
    private JButton myFridgeButton;
    private JButton searchNutritonButton;
    private JButton searchRecipeButton;
    private JButton addRecipeButton;
    private JButton logInButton;
    private JButton signUpButton;

    public frame() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(mainFrame);
        this.setVisible(true);

        searchRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchRecipe search = new SearchRecipe();
            }
        });

        searchNutritonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchNutrition nutrition = new SearchNutrition();

            }
        });

        weeklyMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WeeklyMenu menu = new WeeklyMenu();
            }
        });

        myFridgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fridge fridge = new Fridge();
            }
        });

        shoppingListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShoppingList shoppingList = new ShoppingList();
            }
        });

        addRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewRecipe newRecipe = new NewRecipe();
            }

        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SingUp singUp = new SingUp();
            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogIn logIn = new LogIn();
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
        mainFrame = new JPanel();
        mainFrame.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 11, new Insets(10, 10, 10, 10), -1, -1));
        mainFrame.setBackground(new Color(-15946596));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainFrame.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        mainFrame.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(2, 10, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        titlePanel = new JPanel();
        titlePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        titlePanel.setBackground(new Color(-5570596));
        mainFrame.add(titlePanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tittle = new JLabel();
        tittle.setEnabled(true);
        Font tittleFont = this.$$$getFont$$$("Broadway", -1, 36, tittle.getFont());
        if (tittleFont != null) tittle.setFont(tittleFont);
        tittle.setForeground(new Color(-15946596));
        tittle.setHorizontalAlignment(0);
        tittle.setHorizontalTextPosition(0);
        tittle.setText("Nice tittle");
        titlePanel.add(tittle, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        weeklyMenuButton = new JButton();
        weeklyMenuButton.setBackground(new Color(-9906520));
        Font weeklyMenuButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 20, weeklyMenuButton.getFont());
        if (weeklyMenuButtonFont != null) weeklyMenuButton.setFont(weeklyMenuButtonFont);
        weeklyMenuButton.setForeground(new Color(-1));
        weeklyMenuButton.setText("Weekly menu");
        mainFrame.add(weeklyMenuButton, new com.intellij.uiDesigner.core.GridConstraints(5, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        myFridgeButton = new JButton();
        myFridgeButton.setBackground(new Color(-9906520));
        Font myFridgeButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 20, myFridgeButton.getFont());
        if (myFridgeButtonFont != null) myFridgeButton.setFont(myFridgeButtonFont);
        myFridgeButton.setForeground(new Color(-1));
        myFridgeButton.setText("My fridge");
        mainFrame.add(myFridgeButton, new com.intellij.uiDesigner.core.GridConstraints(5, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        mainFrame.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(5, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        mainFrame.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        shoppingListButton = new JButton();
        shoppingListButton.setBackground(new Color(-9906520));
        shoppingListButton.setEnabled(true);
        Font shoppingListButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 20, shoppingListButton.getFont());
        if (shoppingListButtonFont != null) shoppingListButton.setFont(shoppingListButtonFont);
        shoppingListButton.setForeground(new Color(-3340));
        shoppingListButton.setHideActionText(false);
        shoppingListButton.setHorizontalTextPosition(0);
        shoppingListButton.setText("Shopping list");
        mainFrame.add(shoppingListButton, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        mainFrame.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        searchNutritonButton = new JButton();
        searchNutritonButton.setBackground(new Color(-9906520));
        Font searchNutritonButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 20, searchNutritonButton.getFont());
        if (searchNutritonButtonFont != null) searchNutritonButton.setFont(searchNutritonButtonFont);
        searchNutritonButton.setForeground(new Color(-1));
        searchNutritonButton.setText("Search nutriton");
        mainFrame.add(searchNutritonButton, new com.intellij.uiDesigner.core.GridConstraints(4, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        mainFrame.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(4, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        mainFrame.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(7, 8, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        addRecipeButton = new JButton();
        addRecipeButton.setBackground(new Color(-9906520));
        Font addRecipeButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 20, addRecipeButton.getFont());
        if (addRecipeButtonFont != null) addRecipeButton.setFont(addRecipeButtonFont);
        addRecipeButton.setForeground(new Color(-1));
        addRecipeButton.setText("Add recipe");
        mainFrame.add(addRecipeButton, new com.intellij.uiDesigner.core.GridConstraints(7, 9, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchRecipeButton = new JButton();
        searchRecipeButton.setBackground(new Color(-9906520));
        Font searchRecipeButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 20, searchRecipeButton.getFont());
        if (searchRecipeButtonFont != null) searchRecipeButton.setFont(searchRecipeButtonFont);
        searchRecipeButton.setForeground(new Color(-1));
        searchRecipeButton.setText("Search recipe");
        mainFrame.add(searchRecipeButton, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logInButton = new JButton();
        logInButton.setBackground(new Color(-9770823));
        Font logInButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 14, logInButton.getFont());
        if (logInButtonFont != null) logInButton.setFont(logInButtonFont);
        logInButton.setForeground(new Color(-16100280));
        logInButton.setText(" Log in ");
        mainFrame.add(logInButton, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        signUpButton = new JButton();
        signUpButton.setBackground(new Color(-9770823));
        Font signUpButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 14, signUpButton.getFont());
        if (signUpButtonFont != null) signUpButton.setFont(signUpButtonFont);
        signUpButton.setForeground(new Color(-16100280));
        signUpButton.setText("Sign up");
        mainFrame.add(signUpButton, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return mainFrame;
    }

    //    public static void main(String[] args){
//
//        JFrame window = new frame();
//        SpringApplication.run(DemoApplication.class, args);
//    }

//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//    }
}
