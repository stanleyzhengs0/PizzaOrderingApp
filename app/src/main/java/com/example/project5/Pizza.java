package com.example.project5;

import java.util.ArrayList;

/**
 This class provides it subclasses to specify toppings, crust, size,
 price and methods to add and remove toppings
 @author Stanley Zheng, Jamie Pan
 */
public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;


    /**
     * initializes toppings, size, and crust for pizzas
     * @param crust the crust for the pizza
     */
    public Pizza(Crust crust) {
        toppings = new ArrayList<Topping>();
        this.crust = crust;
        size = new Size();
    }

    /**
     * provides any subclass a method signature to determine the price of pizza
     * @return returns the price of the pizza
     */
    public abstract double price();

    /**
     * add toppings to the pizza
     * @param obj takes in a topping object
     * @return return true if a topping was added
     */
    public boolean add(Object obj) {
        Topping top = (Topping) obj;
        toppings.add(top);
        return true;
    }

    /**
     * remove toppings to the pizza
     * @param obj takes in a topping object
     * @return return true if a topping was removed
     */
    public boolean remove(Object obj) {
        Topping top = (Topping) obj;
        toppings.remove(top);
        return true;
    }


    /**
     * sets size of pizza
     * @param pizzaSize pizza size
     */
    public void setSize(String pizzaSize) {
        size.setPizzaSize(pizzaSize);
    }

    /**
     * gets crust of pizza
     * @return pizza crust type
     */
    public String getCrust() {
        return crust.getCrustType();
    }

    /**
     * gets arraylist of toppings
     * @return toppings arraylist
     */
    public ArrayList<Topping> getToppingArrayList() {
        return toppings;
    }

    /**
     * gets all the toppings on a pizza
     * @return string representation of toppings on a pizza
     */
    public String getToppings() {
        String string = "  -";
        for (int i = 0; i < toppings.size(); i++) {
            if (i == toppings.size() - 1) {
                string += toppings.get(i);
            }
            else {
                string += toppings.get(i) + ", ";
            }
        }
        return string;
    }

    /**
     * gets size of pizza
     * @return size of pizza
     */
    public String getSize() {
        return size.getPizzaSize();
    }

}