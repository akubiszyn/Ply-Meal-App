package com.example.demo;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class WeeklyMenu extends JFrame {
    private JPanel mainWeeklyMenuFrame;
    private JPanel WeeklyMenuPanel;
    private JButton TuesdayButton;
    private JButton WednesdayButton;
    private JButton MondayButton;
    private JButton FridayButton;
    private JButton ThursdayButton;
    private JButton SaturdayButton;
    private JButton SundayButton;

    public WeeklyMenu(int client_id) {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(mainWeeklyMenuFrame);
        this.setVisible(true);

        MondayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayMenu monday = new DayMenu("Monday", client_id);
            }
        });
        TuesdayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayMenu tuesday = new DayMenu("Tuesday", client_id);
            }
        });

        WednesdayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayMenu wednesday = new DayMenu("Wednesday", client_id);
            }
        });
        ThursdayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayMenu thursday = new DayMenu("Thursday", client_id);
            }
        });
        FridayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayMenu friday = new DayMenu("Friday", client_id);
            }
        });
        SaturdayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayMenu saturday = new DayMenu("Saturday", client_id);
            }
        });
        SundayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayMenu sunday = new DayMenu("Sunday", client_id);
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
        mainWeeklyMenuFrame = new JPanel();
        mainWeeklyMenuFrame.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 6, new Insets(20, 20, 20, 20), -1, -1));
        mainWeeklyMenuFrame.setBackground(new Color(-15946596));
        mainWeeklyMenuFrame.setForeground(new Color(-1));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainWeeklyMenuFrame.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(3, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        FridayButton = new JButton();
        FridayButton.setBackground(new Color(-9906520));
        Font FridayButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, FridayButton.getFont());
        if (FridayButtonFont != null) FridayButton.setFont(FridayButtonFont);
        FridayButton.setForeground(new Color(-3340));
        FridayButton.setMargin(new Insets(5, 5, 5, 5));
        FridayButton.setText("Friday");
        mainWeeklyMenuFrame.add(FridayButton, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, 35), null, 0, false));
        ThursdayButton = new JButton();
        ThursdayButton.setBackground(new Color(-9906520));
        Font ThursdayButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, ThursdayButton.getFont());
        if (ThursdayButtonFont != null) ThursdayButton.setFont(ThursdayButtonFont);
        ThursdayButton.setForeground(new Color(-3340));
        ThursdayButton.setMargin(new Insets(5, 5, 5, 5));
        ThursdayButton.setText("Thursday");
        mainWeeklyMenuFrame.add(ThursdayButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        mainWeeklyMenuFrame.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        SaturdayButton = new JButton();
        SaturdayButton.setBackground(new Color(-9906520));
        Font SaturdayButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, SaturdayButton.getFont());
        if (SaturdayButtonFont != null) SaturdayButton.setFont(SaturdayButtonFont);
        SaturdayButton.setForeground(new Color(-3340));
        SaturdayButton.setMargin(new Insets(5, 5, 5, 5));
        SaturdayButton.setText("Saturday");
        mainWeeklyMenuFrame.add(SaturdayButton, new com.intellij.uiDesigner.core.GridConstraints(2, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SundayButton = new JButton();
        SundayButton.setBackground(new Color(-9906520));
        Font SundayButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, SundayButton.getFont());
        if (SundayButtonFont != null) SundayButton.setFont(SundayButtonFont);
        SundayButton.setForeground(new Color(-3340));
        SundayButton.setMargin(new Insets(5, 5, 5, 5));
        SundayButton.setText("Sunday");
        mainWeeklyMenuFrame.add(SundayButton, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        mainWeeklyMenuFrame.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        mainWeeklyMenuFrame.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        WeeklyMenuPanel = new JPanel();
        WeeklyMenuPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        WeeklyMenuPanel.setBackground(new Color(-5570596));
        mainWeeklyMenuFrame.add(WeeklyMenuPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setEnabled(true);
        Font label1Font = this.$$$getFont$$$("Broadway", -1, 36, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-15946596));
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("Weekly Menu");
        WeeklyMenuPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TuesdayButton = new JButton();
        TuesdayButton.setBackground(new Color(-9906520));
        Font TuesdayButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, TuesdayButton.getFont());
        if (TuesdayButtonFont != null) TuesdayButton.setFont(TuesdayButtonFont);
        TuesdayButton.setForeground(new Color(-3340));
        TuesdayButton.setMargin(new Insets(5, 5, 5, 5));
        TuesdayButton.setText("Tuesday");
        mainWeeklyMenuFrame.add(TuesdayButton, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        WednesdayButton = new JButton();
        WednesdayButton.setBackground(new Color(-9906520));
        Font WednesdayButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, WednesdayButton.getFont());
        if (WednesdayButtonFont != null) WednesdayButton.setFont(WednesdayButtonFont);
        WednesdayButton.setForeground(new Color(-3340));
        WednesdayButton.setMargin(new Insets(5, 5, 5, 5));
        WednesdayButton.setText("Wednesday");
        mainWeeklyMenuFrame.add(WednesdayButton, new com.intellij.uiDesigner.core.GridConstraints(1, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        MondayButton = new JButton();
        MondayButton.setBackground(new Color(-9906520));
        Font MondayButtonFont = this.$$$getFont$$$("Goudy Old Style", Font.BOLD, 26, MondayButton.getFont());
        if (MondayButtonFont != null) MondayButton.setFont(MondayButtonFont);
        MondayButton.setForeground(new Color(-3340));
        MondayButton.setMargin(new Insets(5, 5, 5, 5));
        MondayButton.setText("Monday");
        mainWeeklyMenuFrame.add(MondayButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return mainWeeklyMenuFrame;
    }

    ;

}
