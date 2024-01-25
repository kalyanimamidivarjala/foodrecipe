package com.food;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@MultipartConfig
@WebServlet("/AddRecipeServlet")
public class AddRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String recipeName = request.getParameter("recipeName");
		String description=request.getParameter("description");
         String ingredients = request.getParameter("ingredients");
         String cookingDirections = request.getParameter("cookingDirections");
         InputStream image = request.getPart("foodImage").getInputStream();
         Food food=new Food();
         food.setEmail(email);
         food.setRecipeName(recipeName);
         food.setDescription(description);
         food.setIngredients(ingredients);
         food.setCookingDirections(cookingDirections);
         food.setImage(image);
         FoodDao foodDao=new FoodDao();
         
         int result =foodDao.addFood(food);
         PrintWriter writer=response.getWriter();
         
		if(result==1) {
		writer.println(result+" RECIPE ADDED sucessfuly");
		RequestDispatcher dispatcher = request.getRequestDispatcher("recipeDetails.jsp");
    	dispatcher.forward(request, response);
		}
		else
		{
			response.setContentType("text/html");
        	writer.println("<b>Recipe Not Saved please Check once</b>");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("addrecipe.html");
        	dispatcher.include(request, response);
		}
		
	}

}
