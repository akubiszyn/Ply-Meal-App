package com.example.demo;

import com.example.demo.Food.FoodController;
import com.example.demo.Food.recipe.Recipe;
import com.example.demo.Food.recipe.RecipeResponse;
import com.example.demo.Food.recipe.ingredients.IngredientDescription;
import com.example.demo.Food.recipe.ingredients.Ingredients;
import com.example.demo.Food.recipe.steps.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class InsertRecipes implements CommandLineRunner {
        @Autowired
        private JdbcTemplate jdbcTemplate;


    @Override
    public void run(String... args) {
        FoodController foodController = new FoodController();
        ArrayList<RecipeResponse> recipes = new ArrayList<>();
        ArrayList<String> foods = new ArrayList<String>(Arrays.asList("stew", "spaghetti", "bowl", "lasagne"));
        int idx = 0;
        int ingredientId = 521;
        int step_id = 351;
        for (String food : foods) {
            recipes.add(foodController.getRecipe(food, "5"));
            for (Recipe recipe : recipes.get(idx).getResults()) {
                jdbcTemplate.update("INSERT INTO recipe VALUES (?, ?, ?)", recipe.getRecipe_id(), recipe.getName(), recipe.getImage_url());
                Ingredients ingredients = foodController.getRecipeIngredients(recipe);
                for (IngredientDescription ingredient : ingredients.getIngredients()) {

                    if (jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOOD WHERE name = \'" + ingredient.getName() + "\'", Integer.class) == 0)
                    {
                        ingredient.setId(0);
                    }
                    else{
                        int id = jdbcTemplate.queryForObject("SELECT item_id FROM FOOD WHERE name = \'" + ingredient.getName() + "\'", Integer.class);
                        ingredient.setId(id);
                    }
                    String query = "SELECT COUNT(*) FROM FOOD WHERE item_id = " + ingredient.getId();
                    if (jdbcTemplate.queryForObject(query, Integer.class) == 0) {
                        jdbcTemplate.update("INSERT INTO food VALUES (?, ?)", ingredient.getId() ,ingredient.getName());

                    }
                    float amount = ingredient.getAmount().getMetric().getValue();
                    String unit = ingredient.getAmount().getMetric().getUnit();
                    jdbcTemplate.update("INSERT INTO ingredient VALUES (?, ?, ?, ?, ?)", ingredientId, recipe.getRecipe_id(), ingredient.getId(), amount, unit);


                    ingredientId++;
                }
                for (Step step : recipe.getSteps()) {
                    jdbcTemplate.update("INSERT INTO recipe_step VALUES (?, ?, ?, ?)", step_id, step.getNumber(), step.getStep(), step.getRecipeId());
                    step_id++;
                }

            }
            idx++;
        }
    }
//    public static void main(String[] args){
//        SpringApplication.run(InsertRecipes.class, args);
//    }
//
}
