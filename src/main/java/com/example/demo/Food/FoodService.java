package com.example.demo.Food;
import com.example.demo.Food.ingredient.Ingredient;
import com.example.demo.Food.ingredient_id.IngredientId;
import com.example.demo.Food.ingredient_id.IngredientResponse;
import com.example.demo.Food.recipe.Recipe;
import com.example.demo.Food.recipe.RecipeResponse;
import com.example.demo.Food.recipe.steps.RecipeSteps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class FoodService {
    private static final String INGREDIENT_URL = "https://api.spoonacular.com/food/ingredients/";
    private static final String INGREDIENT_ID_URL = "https://api.spoonacular.com/food/ingredients/search?";
    private static final String RECIPE_STEPS_URL = "https://api.spoonacular.com/recipes/";
    private static final String RECIPE_SEARCH_URL = "https://api.spoonacular.com/recipes/complexSearch?";
//    apiKey=75ea5da8b94e4d0f839c3c3767c9d791
//    b32dcd69c8324e7c96de4e04aeecd7ea
    private static final String API_KEY = "apiKey=b32dcd69c8324e7c96de4e04aeecd7ea";

    private final RestTemplate restTemplate = new RestTemplate();

    public IngredientId getIngredient(String food) {
        String jsonId = restTemplate.getForObject(INGREDIENT_ID_URL + API_KEY + "&query={food}&number=1", String.class, food);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        IngredientResponse response = new IngredientResponse();
        try {
            response = objectMapper.readValue(jsonId, IngredientResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response.getResults().get(0);
    }

    public Ingredient getIngredientInformation(String id, String amount, String unit){
        Ingredient ingredientInformation;
        String jsonIngredient = restTemplate.getForObject(INGREDIENT_URL + "{id}/information?amount={amount}&unit={unit}&" + API_KEY, String.class, id, amount, unit );
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            ingredientInformation = objectMapper.readValue(jsonIngredient, Ingredient.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ingredientInformation;
    }
    public RecipeResponse getRecipe(String food, String number){
        String jsonRecipeSearch = restTemplate.getForObject(RECIPE_SEARCH_URL + API_KEY + "&query={food}&&number={number}", String.class, food, number );
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RecipeResponse response = new RecipeResponse();
        try {
            response = objectMapper.readValue(jsonRecipeSearch, RecipeResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }


    public void getRecipeSteps(RecipeResponse recipes) {
        for (Recipe recipe : recipes.getResults()) {
            String jsonRecipeSteps = restTemplate.getForObject(RECIPE_STEPS_URL + "{id}/analyzedInstructions?" + API_KEY, String.class, recipe.getRecipe_id().toString());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ArrayList<RecipeSteps> response;
            try {
                response = objectMapper.readValue(jsonRecipeSteps, new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            for (var step : response.get(0).getSteps()) {
                step.setRecipeId(recipe.getRecipe_id());
            }
            recipe.setSteps(response.get(0).getSteps());
        }
    }

}
