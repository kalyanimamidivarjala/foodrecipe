package com.food;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/UpdateFoodServlet")
public class UpdateFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recipeName=request.getParameter("recipeName");
		String ingredients=request.getParameter("ingredients");
		String cookingDirections=request.getParameter("cookingDirections");
		
		Food food=new Food();
		food.setRecipeName(recipeName);
		food.setIngredients(ingredients);
		food.setCookingDirections(cookingDirections);
		
		FoodDao dao=new FoodDao();
		
		Part newImagePart = request.getPart("image");
	     if (newImagePart != null && newImagePart.getSize() > 0) {
	    	 	
	            food.setImage(newImagePart.getInputStream());
	        } else {
	        	Food  resultSet = dao.editFood(recipeName);
	        	food.setImage(resultSet.getImage());
	        }
		int result=dao.updateFood(food);
		if(result == 1)
	     {  	RequestDispatcher dispatcher = request.getRequestDispatcher("productList.jsp");
	        	dispatcher.forward(request, response);
	     }
}

	}


