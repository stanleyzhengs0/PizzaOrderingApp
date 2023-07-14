package com.example.project5;

import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 This class creates an order that hold the number pizzas created
 @author Stanley Zheng, Jamie Pan
 */
public class Order implements Customizable {

    private int orderNum;
    private double price;
    private ArrayList<Pizza> pizzaList;

    private static final double SALES_TAX = 0.06625;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    /**
     * initializes the price, list of pizzas and order number
     * @param orderNumber
     */
    public Order(int orderNumber) {
        orderNum = orderNumber;
        price = 0.00;
        pizzaList = new ArrayList<Pizza>();
    }

    /**
     * adds a pizza to a order
     * @param obj passes in a pizza
     * @return returns true if the pizza was added
     */
    @Override
    public boolean add(Object obj) {
        Pizza pizza = (Pizza) obj;
        pizzaList.add(pizza);
        price += pizza.price();
        return true;
    }

    /**
     * removes a pizza to a order
     * @param obj passes in a pizza
     * @return returns true if the pizza was removed
     */
    @Override
    public boolean remove(Object obj) {
        Pizza pizza = (Pizza) obj;
        pizzaList.remove(pizza);
        price -= pizza.price();
        return true;
    }


    /**
     *  gets price of pizza order, before adding sales tax
     * @return subtotal of order
     */
    public double getPrice() {
        return price;
    }

    /**
     *  gets price of pizza order, after adding sales tax
     * @return total of order
     */
    public String getTotalPrice() {
        return df.format(price + (price * SALES_TAX));
    }

    /**
     *  gets arraylist of pizzas
     * @return pizzas stored in arraylist
     */
    public ArrayList<Pizza> getPizzaList() {
        return pizzaList;
    }

    /**
     *  gets the order number
     * @return order number of order
     */
    public int getOrderNum() {
        return orderNum;
    }
}
