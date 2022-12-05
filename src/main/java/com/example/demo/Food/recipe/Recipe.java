package com.example.demo.Food.recipe;

import com.example.demo.Food.recipe.steps.Step;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Recipe {
    @JsonProperty("id")
    private Integer recipe_id;
    @JsonProperty("title")
    private String name;
    @JsonProperty("image")
    private String image_url;
    private ArrayList<Step> steps;

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public Recipe(int id, String title, String image, ArrayList<Step> steps) {
        this.recipe_id = id;
        this.name = title;
        this.image_url = image;
        this.steps = steps;
    }
    public Recipe(){

    }

    public Integer getRecipe_id() {
        return recipe_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
