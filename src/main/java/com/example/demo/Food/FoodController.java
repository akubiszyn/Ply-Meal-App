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

    public Ingredient getIngredient(String food, String amount, String unit){
        String id = foodService.getIngredient(food);
        Ingredient ingredient = foodService.getIngredientInformation(id, amount, unit);
        return ingredient;
    }
    public RecipeResponse getRecipe(String food, String number) {
        RecipeResponse recipes = foodService.getRecipe(food, number);
        foodService.getRecipeSteps(recipes);
        return recipes;
    }
    public static void main(String[] args){
//        FoodController foodController = new FoodController();
//        Ingredient ingredient = foodController.getIngredient("pineapple", "100", "gram");
//        RecipeResponse recipes = foodController.getRecipe("pasta", "10");
//        ingredient.getNutrition().getNutrients().forEach(nutrient ->System.out.println(nutrient.getName() + ": " + nutrient.getAmount()));
//        System.out.println(recipes.getResults().get(0).getSteps().get(3));


    }
}
