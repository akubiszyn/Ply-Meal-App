package com.example.demo.Food;

import com.example.demo.Food.ingredient.Ingredient;
import com.example.demo.Food.ingredient_id.IngredientId;
import com.example.demo.Food.recipe.Recipe;
import com.example.demo.Food.recipe.RecipeResponse;
import com.example.demo.Food.recipe.ingredients.Ingredients;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController {
    private final FoodService foodService = new FoodService();

    public FoodController(){

    }

    public Ingredient getIngredient(String food, String amount, String unit){
        IngredientId ingredientId = foodService.getIngredient(food);
        Ingredient ingredient = foodService.getIngredientInformation(ingredientId.getId(), amount, unit);
        ingredient.setName(ingredientId.getName());
        return ingredient;
    }
    public RecipeResponse getRecipe(String food, String number) {
        RecipeResponse recipes = foodService.getRecipe(food, number);
        foodService.getRecipeSteps(recipes);
        return recipes;
    }
    public Ingredients getRecipeIngredients(Recipe recipe){
        Ingredients ingredients = foodService.getRecipeingredients(recipe);
        return ingredients;
    }
    public static void main(String[] args){
        FoodController foodController = new FoodController();
//        Ingredient ingredient = foodController.getIngredient("frozen peas", "100", "gram");
//        Ingredients ingredients = new Ingredients();
//        RecipeResponse recipes = foodController.getRecipe("pasta", "4");
//        for (int i = 0; i < 4; i++) {
//            ingredients = foodController.getRecipeIngredients(recipes.getResults().get(2));
//        }
//        ingredient.getNutrition().getNutrients().forEach(nutrient ->System.out.println(nutrient.getName() + ": " + nutrient.getAmount()));
//        System.out.println(recipes.getResults().get(0).getSteps().get(3));


    }
}
