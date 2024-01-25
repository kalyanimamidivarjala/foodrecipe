package com.food;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search=request.getParameter("search");
		FoodDao dao=new FoodDao();
	   List<Food> food	=dao.search(search);
	   System.out.println(food);
	   request.setAttribute("search", food);
	   RequestDispatcher dispatcher=request.getRequestDispatcher("view.jsp");
	   dispatcher.forward(request, response);
		
	}

}
