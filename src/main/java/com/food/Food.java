package com.food;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Food {
	private String email;
    private String recipeName;
    private String description;
    private String ingredients;
    private String cookingDirections;
    private InputStream image;
  //converting image to base 64
    public String getBase64FoodImage() {
        try {
            return Base64.getEncoder().encodeToString(image.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Constructors, getters, and setters

    public Food() {
    }
    
    

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
    

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCookingDirections() {
        return cookingDirections;
    }

    public void setCookingDirections(String cookingDirections) {
        this.cookingDirections = cookingDirections;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
