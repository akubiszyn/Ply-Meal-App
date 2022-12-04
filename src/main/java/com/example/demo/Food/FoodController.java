package com.example.demo.Food;

import com.example.demo.Food.ingredient.Ingredient;
import com.example.demo.Food.recipe.Recipe;
import com.example.demo.Food.recipe.steps.RecipeSteps;
import com.example.demo.Food.recipe.steps.Step;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController {
    private final FoodService foodService = new FoodService();

    public FoodController(){

    }

    public Ingredient getIngredient(String food){
        String id = foodService.getIngredient(food);
        Ingredient ingredient = foodService.getIngredientInformation(id);
        return ingredient;
    }
    public Recipe getRecipe(String food) {
        Recipe recipe = foodService.getRecipe(food);
        RecipeSteps steps = foodService.getRecipeSteps(recipe);
        return recipe;
    }
    public static void main(String[] args){
        FoodController foodController = new FoodController();
        Ingredient ingredient = foodController.getIngredient("pineapple");
        Recipe recipe = foodController.getRecipe("pasta");
        System.out.println(ingredient.getNutrition().getNutrients().get(4));
        System.out.println(recipe.getSteps().get(3));


    }
}
