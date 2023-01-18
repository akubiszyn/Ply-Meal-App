package com.example.demo.Food.recipe.ingredients;

import com.example.demo.Food.FoodController;
import com.example.demo.Food.FoodService;
import com.example.demo.Food.ingredient_id.IngredientId;

public class IngredientDescription {
    private int id;

    private static int foodId = 166;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id == 0){
            this.id = foodId ;
            foodId ++;
        }
        else{
            this.id = id;
        }

    }

    private String name;
    private Amount amount;
    public IngredientDescription(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
