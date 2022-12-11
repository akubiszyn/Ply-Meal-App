package com.example.demo.Food.ingredient_id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredientId {
    @JsonProperty("id")
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
