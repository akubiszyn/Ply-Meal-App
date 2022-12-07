import com.example.demo.Food.FoodController;
import com.example.demo.Food.ingredient.Ingredient;
import com.example.demo.Food.ingredient.Nutrients;
import com.example.demo.Food.ingredient.Nutrition;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchNutrition extends JFrame {


    private JPanel nutritionMainFrame;
    private JLabel searchNutritionTitle;
    private JTextField product;
    private JTextField amount;
    private JTextArea typeAmountTextArea;
    private JButton buttonOK;
    private JLabel nutritionsLabel;

    public SearchNutrition() {
        super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setBounds(300, 50, 900, 700);
        this.setContentPane(nutritionMainFrame);
        this.setVisible(true);
        this.buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String food = product.getText();
                String strAmount = amount.getText();
                FoodController foodController = new FoodController();
                Ingredient ingredient = foodController.getIngredient(food, strAmount, "gram");
                String finalText = "";
                ArrayList<Nutrients>nutrientsList = ingredient.getNutrition().getNutrients();
                ArrayList<Nutrients> nutrients = new ArrayList<>(Arrays.asList(nutrientsList.get(30), nutrientsList.get(24), nutrientsList.get(9), nutrientsList.get(1)));
                for (Nutrients nutrient : nutrients) {
                    String name = nutrient.getName();
                        finalText += nutrient.getName() + ": " + nutrient.getAmount().toString() + " " + nutrient.getUnit() + " ";
                }

                nutritionsLabel.setText(finalText);

            }
        });

    }
}
