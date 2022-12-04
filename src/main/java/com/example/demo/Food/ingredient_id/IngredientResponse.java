package com.example.demo.Food.ingredient_id;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class IngredientResponse {
    @JsonProperty("results")
    private ArrayList<IngredientId> results;

    public IngredientResponse(ArrayList<IngredientId> results) {
        this.results = results;
    }
    public IngredientResponse(){

    }

    public void setResults(ArrayList<IngredientId> results) {
        this.results = results;
    }

    public ArrayList<IngredientId> getResults() {
        return this.results;
    }
}
