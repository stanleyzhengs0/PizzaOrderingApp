package com.example.project5;

/**
 This class is a subclass of Pizza that contains specific toppings, crust and prices that match the
 * version of the pizza class that's being created i.e. BBQ Chicken.
 @author Stanley Zheng, Jamie Pan
 */
public class BBQChicken extends Pizza {

    private String[] toppingList;
    private String type;

    private static final double SMALL_PRICE = 13.99;
    private static final double MEDIUM_PRICE = 15.99;
    private static final double LARGE_PRICE = 17.99;


    /**
     * creates a pizza object that holds its crust, style, toppings, and price values
     * @param crust passes the crust of the pizza
     * @param type passes the type/style of the pizza
     */
    public BBQChicken(Crust crust, String type) {
        super(crust);
        toppingList = new String[]{"BBQ chicken", "green pepper", "provolone", "cheddar"};
        fillToppingList(toppingList);
        this.type = type;
    }

    /**
     fills the Toppings array list with the preset toppings for a BBQ Chicken pizza
    */
    private void fillToppingList(String[] toppingList) {
        for (int i = 0; i < toppingList.length; i++) {
            getToppingArrayList().add(new Topping(toppingList[i]));
        }
    }

    /**
     *  overrides the price method in the superclass to give a unique price for BBQ chicken pizzas
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
        return "BBQ chicken, " + type + ", " + getCrust() + ", " + getSize() + ", $" + price() + "\n" + getToppings();
    }
}
