package com.example.demo.Food.ingredient_id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredientId {
    @JsonProperty("id")
    private String id;

    public IngredientId(String id) {
        this.id = id;
    }
    public IngredientId(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
