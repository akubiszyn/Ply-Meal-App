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


}
