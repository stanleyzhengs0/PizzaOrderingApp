package com.example.project5;

import java.text.DecimalFormat;

/**
 This class is a subclass of Pizza that contains specific toppings, crust and prices that match
 * the version of the pizza class that's being created i.e. Build Your Own.
 @author Stanley Zheng, Jamie Pan
 */
public class BuildYourOwn extends Pizza {

    private String type;

    private static final double SMALL_PRICE = 8.99;
    private static final double MEDIUM_PRICE = 10.99;
    private static final double LARGE_PRICE = 12.99;
    private static final double TOPPING_COST = 1.59;

    private static final DecimalFormat df = new DecimalFormat("0.00");


    /**
     * creates a pizza object that holds its crust, style, toppings, and price values
     * @param crust passes the crust of the pizza
     * @param type passes the type/style of the pizza
     */
    public BuildYourOwn(Crust crust, String type) {
        super(crust);
        this.type = type;
    }

    /**
     *  overrides the price method in the superclass to give a unique price for build your own pizzas
     * @return returns the price of the pizza
     */
    @Override
    public double price() {
        double extraCost = TOPPING_COST * getToppingArrayList().size();
        if (getSize().equals("small")) {
            return SMALL_PRICE + extraCost;
        }
        if (getSize().equals("medium")) {
            return MEDIUM_PRICE + extraCost;
        }
        return LARGE_PRICE + extraCost;
    }


    /**
     *  gets the pizza, crust, size, and price as a string
     * @return string representation of pizza and price
     */
    @Override
    public String toString() {
        return "Build your own, " + type + ", " + getCrust() + ", " + getSize() + ", $" + df.format(price()) + "\n" + getToppings();
    }
}

