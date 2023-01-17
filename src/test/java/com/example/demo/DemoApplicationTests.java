package com.example.demo;

import com.example.demo.Food.FoodController;
import com.example.demo.Food.ingredient.Ingredient;
import com.example.demo.Food.recipe.RecipeResponse;
import com.example.demo.Food.recipe.nutrients.RecipeNutrients;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.*;

//@SpringBootTest
class DemoApplicationTests {
	@Test
	void small() {
		Assertions.assertEquals(2, 2);
	}



	// --------------- CHECK LOGIN ------------------
	@Test
	void get_client() {
		int quantity = 0;
		String sql = "select client_id from client";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			if (rs.next()) {
				quantity += 1;
			}
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
		Assertions.assertTrue(quantity>0);
	}

	@Test
	void add_user(){
		String username = "Marcelina";
		// count users before
		int quantity_before = 0;
		String sql = "select count(client_id) from client";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			if (rs.next()) {
				quantity_before = rs.getInt(1);
			}
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}

		// check if there is already this username
		int quantity_username = 0;
		String sql2 = "select count(client_id) from client where name = '" + username + "'";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql2);) {
			if (rs.next()) {
				quantity_username = rs.getInt(1);
			}
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}

		// add user
		SingUp singUp = new SingUp();
		singUp.usernameTextField.setText(username);
		singUp.singUpButton.doClick();

		int quantity_after = 0;
		String sql3 = "select count(client_id) from client";
		try ( Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
			  Statement stmt = conn.createStatement();
			  ResultSet rs = stmt.executeQuery(sql3);) {
			if (rs.next()) {
				quantity_after = rs.getInt(1);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		if (quantity_username > 0) {
			Assertions.assertEquals(quantity_before, quantity_after);
		}
		else {
			Assertions.assertEquals(quantity_before, quantity_after-1);
		}
	}

	// --------------------------- CHECK RECIPE ---------------------
	@Test
	void get_recipes() { // check if there are enough recipes in database
		int quantity = 0;
		String sql = "select count(recipe_id) from recipe";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			if (rs.next()) {
				quantity = rs.getInt(1);
			}
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
		Assertions.assertTrue(quantity>30);
	}

	// --------------------------- CHECK INGREDIENTS ---------------------
	@Test
	void get_ingredients() { // check if there are enough ingredients in database
		int quantity = 0;
		String sql = "select count(item_id) from food";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			if (rs.next()) {
				quantity = rs.getInt(1);
			}
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
		Assertions.assertTrue(quantity>200);
	}

	// --------------------------- CHECK STEPS ---------------------
	@Test
	void get_steps() { // check if there are some recipe steps in database
		int quantity = 0;
		String sql = "select count(step_id) from recipe_step";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			if (rs.next()) {
				quantity = rs.getInt(1);
			}
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
		Assertions.assertTrue(quantity>200);
	}

	@Test
	void check_shopping_list() {
		String sqlInsert = "insert into shopping_list values (1, 1)";
		String sqlDelete = "delete from shopping_list where client_id = 0";
		String sqlSelect = "Select item_id from shopping_list where client_id = 0";
		try (
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
			Statement stmt = conn.createStatement();) {
			ResultSet rsInsert = stmt.executeQuery(sqlInsert);
			ResultSet rsSelect = stmt.executeQuery(sqlSelect);
			if (rsSelect.next()) {
				Assertions.assertEquals(rsSelect.getInt(1), 1);
			}
			ResultSet rsDelete = stmt.executeQuery(sqlDelete);
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	@Test
	void check_shopping_list_food_not_exist() {
		String sqlInsert = "insert into shopping_list values (-1, 1)";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {
			Exception exception = Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
				ResultSet rsInsert = stmt.executeQuery(sqlInsert);
			});
			String exceptionName = exception.getClass().getSimpleName();
			String expectedName = "SQLIntegrityConstraintViolationException";
			Assertions.assertTrue(exceptionName.equals(expectedName));
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	void check_shopping_list_client_not_exist() {
		String sqlInsert = "insert into shopping_list values (1, -1)";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {
			Exception exception = Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
				ResultSet rsInsert = stmt.executeQuery(sqlInsert);
			});
			String exceptionName = exception.getClass().getSimpleName();
			String expectedName = "SQLIntegrityConstraintViolationException";
			Assertions.assertTrue(exceptionName.equals(expectedName));
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	void check_fridge_list() {
		String sqlInsert = "insert into fridge_list values (1, 1, null)";
		String sqlDelete = "delete from fridge_list where client_id = 0";
		String sqlSelect = "Select item_id from fridge_list where client_id = 0";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {
			ResultSet rsInsert = stmt.executeQuery(sqlInsert);
			ResultSet rsSelect = stmt.executeQuery(sqlSelect);
			if (rsSelect.next()) {
				Assertions.assertEquals(rsSelect.getInt(1), 1);
			}
			ResultSet rsDelete = stmt.executeQuery(sqlDelete);
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	@Test
	void check_fridge_list_food_not_exist() {
		String sqlInsert = "insert into fridge_list values (-1, 1, null)";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {
			Exception exception = Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
				ResultSet rsInsert = stmt.executeQuery(sqlInsert);
			});
			String exceptionName = exception.getClass().getSimpleName();
			String expectedName = "SQLIntegrityConstraintViolationException";
			Assertions.assertTrue(exceptionName.equals(expectedName));
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	void check_fridge_list_client_not_exist() {
		String sqlInsert = "insert into fridge_list values (1, -1, null)";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {
			Exception exception = Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
				ResultSet rsInsert = stmt.executeQuery(sqlInsert);
			});
			String exceptionName = exception.getClass().getSimpleName();
			String expectedName = "SQLIntegrityConstraintViolationException";
			Assertions.assertTrue(exceptionName.equals(expectedName));
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	void check_add_to_day_menu() {
		String addSql = "Insert into weekly_menu values (1, 1, 1, (select recipe_id from recipe where name like 'Sandwich with jam'))";
		String selectSql = "Select meal_number from weekly_menu where client_id = 1 and recipe_id = 20 and day_number = 1";
		String deleteSql = "Delete from weekly_menu where client_id = 1 and meal_number = 1 and day_number = 1 and recipe_id = 1";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {
			ResultSet rsAdd = stmt.executeQuery(addSql);
			ResultSet rsSelect = stmt.executeQuery(selectSql);
			if (rsSelect.next()) {
				Assertions.assertEquals(rsSelect.getInt(1), 1);
				ResultSet rsDelete = stmt.executeQuery(deleteSql);
			}
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	void check_add_to_day_menu_recipe_not_exist() {
		String addSql = "Insert into weekly_menu values (1, 1, 1, -1)";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {
			Exception exception = Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
				ResultSet rsInsert = stmt.executeQuery(addSql);
			});
			String exceptionName = exception.getClass().getSimpleName();
			String expectedName = "SQLIntegrityConstraintViolationException";
			Assertions.assertTrue(exceptionName.equals(expectedName));
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	// --------------- CHECK API CONNECTION ------------------
	@Test
	void get_ingredient(){
		FoodController foodController = new FoodController();
		Ingredient ingredient = foodController.getIngredient("blackberries", "100", "gram");
		Assertions.assertTrue(ingredient.getId() == 9042);
		Assertions.assertTrue(ingredient.getNutrition().getNutrients().get(8).getName().equals("Vitamin K"));
		Assertions.assertTrue(ingredient.getNutrition().getNutrients().get(8).getAmount() == 19);
	}
	@Test
	void get_recipe(){
		FoodController foodController = new FoodController();
		RecipeResponse recipe = foodController.getRecipe("cake", "1");
		String step = recipe.getResults().get(0).getSteps().get(5).getStep();
		Assertions.assertTrue(recipe.getResults().size() == 1);
		Assertions.assertTrue(recipe.getResults().get(0).getName().equals("Cake Balls"));
		Assertions.assertTrue(recipe.getResults().get(0).getSteps().size() == 10);
		Assertions.assertTrue(recipe.getResults().get(0).getSteps().get(5).getStep().equals("Place on a lined cookie sheet with wax paper."));

	}
	@Test
	void get_recipe_nutrients(){
		FoodController foodController = new FoodController();
		RecipeNutrients recipeNutrients = foodController.getRecipeNutrients(654812);
		Assertions.assertTrue(recipeNutrients.getCalories().equals("557k"));
		Assertions.assertTrue(recipeNutrients.getCarbs().equals("95g"));
		Assertions.assertTrue(recipeNutrients.getFat().equals("3g"));
		Assertions.assertTrue(recipeNutrients.getProtein().equals("40g"));

	}




	@Test
	void check_add_to_day_menu_client_not_exist() {
		String addSql = "Insert into weekly_menu values (-1, 1, 1, 1)";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {
			Exception exception = Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
				ResultSet rsInsert = stmt.executeQuery(addSql);
			});
			String exceptionName = exception.getClass().getSimpleName();
			String expectedName = "SQLIntegrityConstraintViolationException";
			Assertions.assertTrue(exceptionName.equals(expectedName));
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	void check_delete_from_day_menu() {
		String addSql = "Insert into weekly_menu values (1, 1, 1, (select recipe_id from recipe where name like 'Sandwich with jam'))";
		String selectSql = "Select meal_number from weekly_menu where client_id = 1 and recipe_id = 20 and day_number = 1";
		String deleteSql = "Delete from weekly_menu where client_id = 1 and meal_number = 1 and day_number = 1 and recipe_id = 20";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {
			ResultSet rsAdd = stmt.executeQuery(addSql);
			ResultSet rsSelectAdded = stmt.executeQuery(selectSql);
			if (rsSelectAdded.next()) {
				Assertions.assertEquals(rsSelectAdded.getInt(1), 1);
			}
			ResultSet rsDelete = stmt.executeQuery(deleteSql);
			ResultSet rsSelectDeleted = stmt.executeQuery(selectSql);
			if (rsSelectDeleted.next()) {
				Assertions.fail("Not deleted");
			}
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	void test_add_fav_client_not_exist() {
		String addSql = "Insert into fav_recipe values (-1, 100)";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {
			Exception exception = Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
				ResultSet rsInsert = stmt.executeQuery(addSql);
			});
			String exceptionName = exception.getClass().getSimpleName();
			String expectedName = "SQLIntegrityConstraintViolationException";
			Assertions.assertTrue(exceptionName.equals(expectedName));
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	void test_add_fav_recipe_not_exist() {
		String addSql = "Insert into fav_recipe values (1, -1)";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {
			Exception exception = Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
				ResultSet rsInsert = stmt.executeQuery(addSql);
			});
			String exceptionName = exception.getClass().getSimpleName();
			String expectedName = "SQLIntegrityConstraintViolationException";
			Assertions.assertTrue(exceptionName.equals(expectedName));
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	void test_step_to_recipe_not_existing() {
		String addSql = "Insert into recipe_step values (100, 1, 'description', -1)";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {
			Exception exception = Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
				ResultSet rsInsert = stmt.executeQuery(addSql);
			});
			String exceptionName = exception.getClass().getSimpleName();
			String expectedName = "SQLIntegrityConstraintViolationException";
			Assertions.assertTrue(exceptionName.equals(expectedName));
		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	void check_add_to_fav_recipe() {
		int old_quantity=0;
		int new_quantity=0;
		String addSql = "Insert into fav_recipe values (1, 654959)";
		String selectSql = "Select count(*) from fav_recipe where client_id = 1";
		String deleteSql = "Delete from fav_recipe where client_id = 1 and recipe_id = 654959";
		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "sfojt", "sfojt");
				Statement stmt = conn.createStatement();) {

			ResultSet rsSelect = stmt.executeQuery(selectSql);
			if (rsSelect.next()) {
				old_quantity = rsSelect.getInt(1);
			}

			ResultSet rsAdd = stmt.executeQuery(addSql);
			ResultSet rsSelect2 = stmt.executeQuery(selectSql);
			if (rsSelect2.next()) {
				new_quantity = rsSelect2.getInt(1);
			}
			ResultSet rsDelete = stmt.executeQuery(deleteSql);

			Assertions.assertEquals(old_quantity, new_quantity-1);

		} catch (
				SQLException ex) {
			throw new RuntimeException(ex);
		}
	}



}

