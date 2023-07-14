package com.example.project5;

/**
 This class implements all the flavor of pizza's in the chicago style
 @author Stanley Zheng, Jamie Pan
 */
public class ChicagoPizza implements PizzaFactory{


    /**
     * creates a deluxe pizza in chicago style
     * @return returns a Deluxe pizza
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe(new Crust("Deep Dish"), "Chicago style");
        return pizza;
    }

    /**
     * creates a BBQChicken pizza in chicago style
     * @return returns a BBQChicken pizza
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken(new Crust("Pan"), "Chicago style");
        return pizza;
    }

    /**
     * creates a Meatzza pizza in chicago style
     * @return returns a Meatzza pizza
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza(new Crust("Stuffed"), "Chicago style");
        return pizza;
    }

    /**
     * creates a BuildYourOwn pizza in chicago style
     * @return returns a BuildYourOwn pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn(new Crust("Pan"), "Chicago style");
        return pizza;
    }
}
