package com.example.demo.Food.recipe;

import com.example.demo.Food.recipe.steps.Step;

import java.util.ArrayList;

public class Recipe {
    private Integer id;
    private String title;
    private String image;
    private ArrayList<Step> steps;

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public Recipe(int id, String title, String image, ArrayList<Step> steps) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.steps = steps;
    }
    public Recipe(){

    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
