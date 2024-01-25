package com.food;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String email = request.getParameter("email");
	        HassPassword hass=new HassPassword();
	        String pass=hass.hashPassword(password);
	        FoodDao dao=new FoodDao();
	        int result=dao.registerUser(username, pass, email);
	        PrintWriter writer=response.getWriter();
	        if(result==1)
	        {
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
	        	dispatcher.forward(request, response);
	    		}
	    		else
	    		{
	    			response.setContentType("text/html");
	            	writer.println("<b>Some thing went wrong please signup again</b>");
	            	RequestDispatcher dispatcher = request.getRequestDispatcher("signup.html");
	            	dispatcher.include(request, response);
	        	
	        }
     
	}
	 
	}


