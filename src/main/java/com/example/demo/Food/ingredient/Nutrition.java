package com.example.demo.Food.ingredient;

import com.example.demo.Food.ingredient.Nutrients;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class    Nutrition {
    @JsonProperty("nutrients")
    private ArrayList<Nutrients> nutrients;

    public Nutrition(ArrayList<Nutrients> nutrients) {
        this.nutrients = nutrients;
    }
    public  Nutrition(){

    }
    public ArrayList<Nutrients> getNutrients() {
        return nutrients;
    }

    public void setNutrients(ArrayList<Nutrients> nutrients) {
        this.nutrients = nutrients;
    }
}
