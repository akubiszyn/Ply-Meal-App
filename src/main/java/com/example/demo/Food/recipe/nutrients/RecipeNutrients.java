package com.example.demo.Food.recipe.nutrients;

import com.example.demo.Food.ingredient.Nutrients;

import java.util.ArrayList;

public class RecipeNutrients {
    private String calories;
    private String carbs;
    private String fat;
    private String protein;

    public RecipeNutrients() {
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }
}
