package com.example.demo.Food;

import com.example.demo.Food.ingredient.Ingredient;
import com.example.demo.Food.recipe.Recipe;
import com.example.demo.Food.recipe.RecipeResponse;
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
    public RecipeResponse getRecipe(String food, String number) {
        RecipeResponse recipes = foodService.getRecipe(food, number);
        foodService.getRecipeSteps(recipes);
        return recipes;
    }
    public static void main(String[] args){
        FoodController foodController = new FoodController();
        Ingredient ingredient = foodController.getIngredient("pineapple");
        RecipeResponse recipes = foodController.getRecipe("pasta", "10");
        System.out.println(ingredient.getNutrition().getNutrients().get(4));
        System.out.println(recipes.getResults().get(0).getSteps().get(3));


    }
}
