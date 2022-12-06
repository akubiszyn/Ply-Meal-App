package com.example.demo.Food.recipe;

import java.util.ArrayList;

public class RecipeResponse {
    private ArrayList<Recipe> results;
    public RecipeResponse(ArrayList<Recipe> results) {
        this.results = results;
    }

    public RecipeResponse(){

    }
    public ArrayList<Recipe> getResults() {
        return results;
    }

    public void setResults(ArrayList<Recipe> results) {
        this.results = results;
    }
}
