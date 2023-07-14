package com.example.project5;

/**
 This class creates the size object to hold the size of the pizzas
 @author Stanley Zheng, Jamie Pan
 */
public class Size {

    String pizzaSize;


    /**
    initializes the size of the pizza
     */
    public Size() {
        pizzaSize = "small";
    }

    /**
     gets pizza size
     @return string representation of pizza size
     */
    public String getPizzaSize() {
        return pizzaSize;
    }

    /**
     sets size of pizza
     @param pizzaSize pizza size
     */
    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }
}
