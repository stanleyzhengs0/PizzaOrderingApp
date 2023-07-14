package com.example.project5;

/**
 This class implements all the flavor of pizza's in the New york style
 @author Stanley Zheng, Jamie Pan
 */
public class NYPizza implements PizzaFactory {


    /**
     * creates a deluxe pizza in New York style
     * @return returns a Deluxe pizza
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe(new Crust("Brooklyn"), "New York style");
        return pizza;
    }

    /**
     * creates a BBQChicken pizza in New York style
     * @return returns a BBQChicken pizza
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza(new Crust("Hand tossed"), "New York style");
        return pizza;
    }

    /**
     * creates a Meatzza pizza in New York style
     * @return returns a Meatzza pizza
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken(new Crust("Thin"), "New York style");
        return pizza;
    }

    /**
     * creates a BuildYourOwn pizza in New York style
     * @return returns a BuildYourOwn pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn(new Crust("Hand tossed"), "New York style");
        return pizza;
    }
}
