<!DOCTYPE html>
<html>
<head>
    <title>Update Recipe</title>
    <link rel="stylesheet" type="text/css" href="style2.css">
</head>
<body>
    <h1>Update New Recipe</h1>

    <form action="./UpdateFoodServlet" method="post" enctype="multipart/form-data">
        <label for="recipeName">Recipe Name:</label>
        <input type="text" id="recipeName" value="${resultSet.getRecipeName}" name="recipeName" readonly="readonly">

        <label for="ingredients">Ingredients:</label>
        <textarea id="ingredients" value="${resultSet.getIngredients}" name="ingredients" rows="4" required></textarea>

        <label for="cookingDirections">Cooking Directions:</label>
        <textarea id="cookingDirections" value="${resultSet.getCookingDirections}" name="cookingDirections" rows="6" required></textarea>

        <label for="foodImage">Food Image:</label>
        <input type="file" id="foodImage" value="${resultSet.getImage}" name="foodImage" accept="image/*" required>
         

        <button type="submit" >Update Recipe</button>
       
    </form>
     

   
</body>
</html>