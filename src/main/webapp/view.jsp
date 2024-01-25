<%@page import="com.food.FoodDao"%>
<%@page import="com.food.Food"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="style.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#myinput").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#recipes .container").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                });
            });
        });
    </script>
</head>
<body>
    <h1>Recipe Details</h1>
    <div>
        <input id="myinput" type="text" placeholder="Search by Recipe Name or Ingredients">
    </div>
   
	<table>
		<thead>
			<tr>
				<th>User Mail Id</th>
				<th>Recipe Image</th>
				<th>Recipe Name</th>
				<th>Description</th>
				<th>Ingredients</th>
				<th>Cooking Directions</th>
				<th>Actions</th>
        </tr>
    </thead>
	    
    <tbody>
    	 <c:forEach var="search"  items="<%=new FoodDao().displayRecipes()%>">
    	 <tr>
    	 <td>${search.getEmail()}</td>
    	 <td>
    		<img src="data:image/jpeg;base64,${search.base64FoodImage}" alt="food Image" ">
    		</td>
    		<td><strong> ${search.getRecipeName()}</strong> </td>
    		<td> ${search.getIngredients()}</td>
    	<td><a href="${search.getCookingDirections()}">CookingDirections</a></td>
     	<td><a href="/EditFoodServlet?recipeName=${search.getRecipeName()}">Edit</a>
            <a href="/DeleteServlet?recipeName=${search.getRecipeName()}">Delete</a>
            </td>
     </c:forEach>
     </table>
</body>
</html>
