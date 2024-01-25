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


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		HassPassword hass=new HassPassword();
        String pass=hass.hashPassword(password);
		
		FoodDao dao=new FoodDao();
		boolean satuts=dao.login(username, pass);
		
		if (satuts==true) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("userpage.html");
			dispatcher.forward(request, response);
			
		} else {
			PrintWriter writer=response.getWriter();
			writer.println("PLEASE CHECK YOUR USERNAME AND PASSWORD OTHERWISE YOUR NOT SIGNUO YET");
			RequestDispatcher dispatcher=request.getRequestDispatcher("login.html");
			dispatcher.include(request, response);

		
		}
	}
  

}
