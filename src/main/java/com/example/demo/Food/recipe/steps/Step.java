package com.example.demo.Food.recipe.steps;

import com.example.demo.Food.ingredient.Ingredient;

import java.util.ArrayList;

public class Step {
    private ArrayList<Ingredient> ingredients;
    private int number;
    private String step;
    private Integer recipeId;

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Step(ArrayList<Ingredient> ingredients, int number, String step) {
        this.ingredients = ingredients;
        this.number = number;
        this.step = step;
    }
    public Step(){

    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }



}
