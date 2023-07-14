package com.example.project5;

import java.io.Serializable;

/**
 This class creates the toppings for the pizzas
 @author Stanley Zheng, Jamie Pan
 */
public class Topping implements Serializable {

    private String ingredient;
    private int image;


    /**
    Contructor.
     Initializes instance variables.
     @param ingredient the ingredient name
     */
    public Topping(String ingredient) {
        this.ingredient = ingredient;
    }

    /**
     Contructor.
     Initializes instance variables
     @param ingredient the ingredient name
     @param image the image of the ingredient
     */
    public Topping(String ingredient, int image) {
        this.ingredient = ingredient;
        this.image = image;
    }

    /**
     gets the topping name
     @return name of topping
     */
    public String getIngredient() {
        return ingredient;
    }

    /**
     gets the image of the topping
     @return topping name
     */
    public int getImage() {
        return image;
    }


    /**
     @return the topping ingredient
     */
    @Override
    public String toString() {
        return ingredient;
    }

    /**
     Checks to see if two toppings are the same.
     @param obj the topping to be compared to
     @return true if both toppings are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Topping topping = (Topping) obj;
        return this.ingredient.equalsIgnoreCase(topping.ingredient);
    }
}