package com.food;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String recipeName =request.getParameter("recipeName");
		
		FoodDao dao=new FoodDao();
		int result=dao.deleteFood(email, recipeName);
		if (result==1) {
        	request.setAttribute("result", result);      	
            RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");
            dispatcher.include(request, response);
        	//response.sendRedirect("productList.jsp");
        } else {
            response.sendRedirect("delerror.jsp");
        }
		
	}
	}


