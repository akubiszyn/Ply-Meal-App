package com.example.demo.Food.recipe.ingredients;

public class Metric {
    private String unit;
    private float value;

    public Metric(){

    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
