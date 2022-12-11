package com.example.demo.Food.recipe.steps;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class RecipeSteps {
    @JsonProperty("steps")
    private ArrayList<Step> steps;

    public RecipeSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public RecipeSteps(){

    }

}
