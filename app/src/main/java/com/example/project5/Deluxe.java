package com.example.project5;

/**
 This class is a subclass of Pizza that contains specific toppings, crust and prices that match the
 * the version of the pizza class that's being created i.e. BBQ Chicken.
 @author Stanley Zheng, Jamie Pan
 */
public class Deluxe extends Pizza {

    private String[] toppingList;
    private String type;

    private static final double SMALL_PRICE = 14.99;
    private static final double MEDIUM_PRICE = 16.99;
    private static final double LARGE_PRICE = 18.99;


    /**
     * creates a pizza object that holds its crust, style, toppings, and price values
     * @param crust passes the crust of the pizza
     * @param type passes the type/style of the pizza
     */
    public Deluxe(Crust crust, String type) {
        super(crust);
        toppingList = new String[]{"sausage", "pepperoni", "green pepper", "onion", "mushroom"};
        fillToppingList(toppingList);
        this.type = type;
    }

    /**
     *  fills the Toppings array list with the preset toppings for a deluxe pizza
     */
    private void fillToppingList(String[] toppingList) {
        for (int i = 0; i < toppingList.length; i++) {
            getToppingArrayList().add(new Topping(toppingList[i]));
        }
    }

    /**
     *  overrides the price method in the superclass to give a unique price for Deluxe pizzas
      * @return returns the price of the pizza
     */
    @Override
    public double price() {
        if (getSize().equals("small")) {
            return SMALL_PRICE;
        }
        if (getSize().equals("medium")) {
            return MEDIUM_PRICE;
        }
        return LARGE_PRICE;
    }


    /**
     *  gets the pizza, crust, size, and price as a string
     * @return string representation of pizza and price
     */
    @Override
    public String toString() {
        return "Deluxe, " + type + ", " + getCrust() + ", " + getSize() + ", $" + price() + "\n" + getToppings();
    }
}
