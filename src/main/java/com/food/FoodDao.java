package com.food;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {
	//method to signup
	public int  registerUser(String username, String hashedPassword, String email) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
			int signupresult=0;
			connection=DataBaseUtils.createConnection();
			try {
				preparedStatement=connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?)");
				 preparedStatement.setString(1, username);
                 preparedStatement.setString(2, hashedPassword);
                 preparedStatement.setString(3, email);

                  signupresult = preparedStatement.executeUpdate();
				}
			
                 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return signupresult;
		
	}
	//method to login
	public boolean login(String username, String password) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		 boolean satuts=false;
		 connection=DataBaseUtils.createConnection();
		try {
			preparedStatement=connection.prepareStatement("select * from users where username=? and password_hash=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				satuts=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return satuts;
		
	}
	//method to add recipes
	public int addFood(Food food) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		int saveResult=0;
		try {
			connection=DataBaseUtils.createConnection();
			preparedStatement=connection.prepareStatement("insert into recipes values(?,?,?,?,?,?)");
			preparedStatement.setString(1,food.getEmail());
			preparedStatement.setString(2,food.getRecipeName());
			preparedStatement.setString(3,food.getDescription());
			preparedStatement.setString(4, food.getIngredients());
			preparedStatement.setString(5, food.getCookingDirections());
			preparedStatement.setBinaryStream(6, food.getImage());
			saveResult=preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return saveResult;
		
	}
	
	//method to display the recipes
	public List<Food> displayRecipes() {
		Connection connection=null;
		Statement statement=null;
		List<Food> foodList=new ArrayList<Food>();
		try {
			connection=DataBaseUtils.createConnection();
			statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("select * from recipes");
			while (resultSet.next()) {
				Food food=new Food();
				food.setImage(resultSet.getBinaryStream("image"));
				food.setRecipeName(resultSet.getString("recipe_name"));
				food.setIngredients(resultSet.getString("ingredients"));
				food.setCookingDirections(resultSet.getString("cooking_directions"));
				foodList.add(food);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return foodList;
		
	}
	//method to search
	public List<Food> search(String recipeName) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		List<Food> foodList=new ArrayList<Food>();
				connection=DataBaseUtils.createConnection();
		try {
			preparedStatement=connection.prepareStatement("select * from recipes where recipe_name=? or ingredients=?");
			preparedStatement.setString(1, recipeName);
			preparedStatement.setString(2, recipeName);
			ResultSet resSet= preparedStatement.executeQuery();
			while(resSet.next()) {
				Food food=new Food();
				food.setImage(resSet.getBinaryStream("image"));
				food.setRecipeName(resSet.getString("recipe_name"));
				food.setIngredients(resSet.getString("ingredients"));
				food.setCookingDirections(resSet.getString("cooking_directions"));
				foodList.add(food);
				}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return foodList;
		
	}
	//method to edit 
	public Food editFood(String recipe_name) {
		Food food=null;
		Connection connection=DataBaseUtils.createConnection();
		String sql="SELECT * FROM recipes WHERE recipe_name = ?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, recipe_name);
			ResultSet resultSet= preparedStatement.executeQuery();
			if (resultSet.next()) {
				food=new Food();
				food.setImage(resultSet.getBinaryStream("image"));
				food.setIngredients(resultSet.getString("ingredients"));
				food.setCookingDirections(resultSet.getString("cooking_directions"));
				food.setRecipeName(resultSet.getString("recipe_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return food;
		
	}
	
	public int updateFood(Food foodupdation) {
		Connection connection=DataBaseUtils.createConnection();
		int result=0;
		String sql= "UPDATE recipes SET ingredients=?, cooking_directions=?, image=? WHERE recipe_name=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, foodupdation.getIngredients());
			preparedStatement.setString(2, foodupdation.getCookingDirections());
			preparedStatement.setString(3, foodupdation.getBase64FoodImage());
			preparedStatement.setString(4, foodupdation.getRecipeName());
			result=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	public int deleteFood(String email,String recipeName) {
		Connection connection=DataBaseUtils.createConnection();
		int result=0;
		String sql="delete from recipe where recipe_name=? and email_id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(2, email);
			preparedStatement.setString(1, recipeName);
			result=preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

}


