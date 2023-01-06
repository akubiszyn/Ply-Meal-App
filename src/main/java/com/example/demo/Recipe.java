package com.example.demo;
import javax.swing.*;
import java.net.URL;
import java.sql.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
public class Recipe extends JFrame {

    private JPanel panel;
    private JList ingredientsList;
    private JList stepsList;

    private int recipe_id;

    private String image_url;

    private JButton shoppingButton;
    private JButton favouriteButton;
    private JLabel recipeTitle;
    private JLabel imageLabel;

    public Recipe(String selected) {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(panel);
        //DefaultListModel listModel = new DefaultListModel<>();
        this.recipeTitle.setText(selected);

        DefaultListModel model = new DefaultListModel();
        String sqlQuery = "select recipe_id, image_url from recipe where name like '%" + selected + "%'";
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             //ResultSet rs = stmt.executeQuery("Select name from recipe where name like '%\" + key_word + \"%'");) {
             ResultSet rs = stmt.executeQuery(sqlQuery);) {
            while (rs.next()) {
                this.recipe_id = rs.getInt(1);
                this.image_url = rs.getString(2);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        //get_ingredients(Integer.toString(this.recipe_id));
        get_ingredients(this.recipe_id);
        get_steps(this.recipe_id);

//        try {
////            URL url = new URL("https://spoonacular.com/recipeImages/511728-312x231.jpg");
////            BufferedImage image = ImageIO.read(url);
////            //BufferedImage image = ImageIO.read(new File("https://spoonacular.com/recipeImages/511728-312x231.jpg"));
////            JLabel label = new JLabel(new ImageIcon(image));
////            imagePanel.add(label);
//        }
//        catch(IOException e) {
//            e.printStackTrace();
//        }
//        String urlLocation = "https://spoonacular.com/recipeImages/511728-312x231.jpg";
//        Image image = null;
//        try {
//            URL url = new URL(urlLocation);
//            URLConnection conn = url.openConnection();
//            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
//
//            conn.connect();
//            InputStream urlStream = conn.getInputStream();
//            image = ImageIO.read(urlStream);
//
////            frame.getContentPane().add(lblimage, BorderLayout.CENTER);
////            frame.setSize(image.getWidth(null) + 50, image.getHeight(null) + 50);
////            frame.setVisible(true);
//            JLabel label = new JLabel(new ImageIcon(image));
//            imagePanel.add(label);
//
//        } catch (IOException e) {
//            System.out.println("Something went wrong, sorry:" + e.toString());
//            e.printStackTrace();
//        }
//        try {
//            Image image = null;
//            URL url = new URL("https://spoonacular.com/recipeImages/511728-312x231.jpg");
//            image = ImageIO.read(url);
//            this.setVisible(true);
//            System.out.println("aaa");
//            JLabel label = new JLabel(new ImageIcon(image));
//            this.add(label);
////            imagePanel.add(new ImageIcon(image));
////            ImageIcon image = new ImageIcon(c);
////            jXImageView1.setImage(image);
//        }
//        catch(IOException e) {
//            e.printStackTrace();
////        }
        Image image = null;
        try {
            URL url = new URL(image_url);
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
//
////        JFrame frame = new JFrame();
////        frame.setSize(300, 300);
////        JLabel label = new JLabel(new ImageIcon(image));
////        frame.add(label);
////        frame.setVisible(true);
            imageLabel.setIcon(new ImageIcon(image));
////        imagePanel.addIcon(new ImageIcon(image));
            this.setVisible(true);
    }

    public void get_ingredients(int key_word) {
        DefaultListModel model = new DefaultListModel();
        String result, quantity, unit, name;
        String sqlQuery = "SELECT i.amount, i.unit, f.name\n" +
                "FROM food f INNER JOIN ingredient i USING (item_id)\n" +
                "WHERE i.recipe_id = " + key_word;
        //'"+ key_word +"'
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             //ResultSet rs = stmt.executeQuery("Select name from recipe where name like '%\" + key_word + \"%'");) {
             ResultSet rs = stmt.executeQuery(sqlQuery);) {
            while (rs.next()) {
                quantity = rs.getString(1);
                unit = rs.getString(2);
                if (unit == null) {
                    unit = "";
                }
                System.out.println(unit);
                name = rs.getString(3);
                result = quantity + " " + unit + " " + name;
                model.addElement(result);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        ingredientsList.setModel(model);
    }

    public void get_steps(int key_word) {
        DefaultListModel model = new DefaultListModel();
        String result, step_number, step;
        String sqlQuery = "SELECT step_number, description FROM recipe_step WHERE recipe_id = " + key_word + "ORDER BY step_number";
        //'"+ key_word +"'
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
             Statement stmt = conn.createStatement();
             //ResultSet rs = stmt.executeQuery("Select name from recipe where name like '%\" + key_word + \"%'");) {
             ResultSet rs = stmt.executeQuery(sqlQuery);) {
            while (rs.next()) {
                step_number = rs.getString(1);
                step = rs.getString(2);
                if (step.length() > 200) {
                    String new_step, part1, part2, part3, part4;
////                    String[] parts = step.split("(?=\\D)", 2);
////                    step = parts[0] + "wwww" + parts[1];
                    part1 = step.substring(0, step.length() / 4);
                    System.out.println(part1);
                    part2 = step.substring(step.length() / 4 + 1, step.length() / 4 * 2);
                    System.out.println(part2);
                    part3 = step.substring(step.length() / 4 * 2 + 1, step.length() / 4 * 3);
                    System.out.println(part1);
                    part4 = step.substring(step.length() / 4 * 3 + 1);
                    System.out.println(part2);
//                    step =  part1 + "wwww" + part2;
                    result = step_number + " " + part1;
                    model.addElement(result);
                    result = "  " + part2;
                    model.addElement(result);
                    result = "  " + part3;
                    model.addElement(result);
                    step_number = "  ";
                    step = part4;
                } else if (step.length() > 100) {
                    String new_step, part1, part2;
////                    String[] parts = step.split("(?=\\D)", 2);
////                    step = parts[0] + "wwww" + parts[1];
                    part1 = step.substring(0, step.length() / 2);
                    System.out.println(part1);
                    part2 = step.substring(step.length() / 2);
                    System.out.println(part2);
//                    step =  part1 + "wwww" + part2;
                    result = step_number + " " + part1;
                    model.addElement(result);
                    step_number = "  ";
                    step = part2;
                }

                result = step_number + " " + step;
                model.addElement(result);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        stepsList.setModel(model);
    }
}
