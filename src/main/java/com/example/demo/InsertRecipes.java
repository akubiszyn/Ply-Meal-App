//package com.example.demo;
//
//import com.example.demo.Food.FoodController;
//import com.example.demo.Food.recipe.Recipe;
//import com.example.demo.Food.recipe.RecipeResponse;
//import com.example.demo.Food.recipe.steps.Step;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@SpringBootApplication
//public class InsertRecipes implements CommandLineRunner {
//        @Autowired
//        private JdbcTemplate jdbcTemplate;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        jdbcTemplate.update("delete from recipe_step");
//        FoodController foodController = new FoodController();
//        ArrayList<RecipeResponse> recipes = new ArrayList<>();
////        , "dumplings", "burger", "tofu", "chicken", "pancakes", "salad", "cake", "soup", "pie"
//        ArrayList<String> foods = new ArrayList<String>(Arrays.asList("pasta"));
//        int idx = 0;
//        int step_id = 1;
//        for (String food : foods) {
//            recipes.add(foodController.getRecipe(food, "4"));
//            for (Recipe recipe : recipes.get(idx).getResults()) {
////                if (recipe == recipes.get(idx).getResults().get(0)){
////                    continue;
////                }
////                jdbcTemplate.update("INSERT INTO recipe VALUES (?, ?, ?)", recipe.getRecipe_id(), recipe.getName(), recipe.getImage_url());
//                for (Step step : recipe.getSteps()) {
//                    jdbcTemplate.update("INSERT INTO recipe_step VALUES (?, ?, ?, ?)", step_id, step.getNumber(), step.getStep(), step.getRecipeId());
//                    step_id++;
//                }
//            }
//            idx++;
//        }
//    }
//    public static void main(String[] args){
//        SpringApplication.run(InsertRecipes.class, args);
//    }
//
//}
