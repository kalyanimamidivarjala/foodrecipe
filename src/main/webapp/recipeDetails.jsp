<%@page import="com.food.FoodDao"%>
<%@page import="com.food.Food"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Recipe Details</title>
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

	 <div>
        <input id="myinput" type="text" placeholder="Search by Recipe Name or Ingredients">
    </div>
    
    <h1>Recipe Details</h1>
  	 <div id="recipes">
        <c:forEach var="food" items="<%=new FoodDao().displayRecipes()%>">
            <div class="container">
                <img src="data:image/jpeg;base64,${food.base64FoodImage}" alt="Food Image">
                <p><strong>Recipe Name:</strong> ${food.getRecipeName()}</p>
                <p><strong>Ingredients:</strong> ${food.getIngredients()}</p>
                <p><strong>Cooking Directions:</strong> ${food.getCookingDirections()}</p>
            </div>
        </c:forEach>
    </div>
</body>
</html>

