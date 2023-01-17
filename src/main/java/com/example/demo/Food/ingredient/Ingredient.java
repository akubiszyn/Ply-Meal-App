package com.example.demo.Food.ingredient;

import com.example.demo.Food.FoodService;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingredient {
    private Integer id;
    private String name;
    private String image;

    @JsonProperty("nutrition")
    private Nutrition nutrition;

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public Ingredient(){

    }
    public Ingredient(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.nutrition = null;
    }
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}
