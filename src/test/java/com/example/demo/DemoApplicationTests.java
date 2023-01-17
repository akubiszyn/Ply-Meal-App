package com.example.demo;

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
}
