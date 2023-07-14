package com.example.project5;

import java.util.ArrayList;

/**
 This class stores all the orders made and allows orders to
 be added and removed
 @author Stanley Zheng, Jamie Pan
 */
public class StoreOrder implements Customizable {

    private ArrayList<Order> orderList;
    private int orderNumber;
    private Order order;


    /**
     * initializes the container that stores orders
     */
    public StoreOrder() {
        orderNumber = 1;
        order = new Order(orderNumber);
        orderList = new ArrayList<Order>();
    }

    /**
     * overrides the add method in customizable to add orders to the store orders list
     * @param obj passes in a order object
     * @return return true if the order was added to the list
     */
    @Override
    public boolean add(Object obj) {
        Order pizzaOrder = (Order) obj;
        orderList.add(pizzaOrder);
        orderNumber++;
        order = new Order(orderNumber);
        return true;
    }

    /**
     * overrides the remove method in customizable to remove orders to the store orders list
     * @param obj passes in a order object
     * @return return true if the order was removed to the list
     */
    @Override
    public boolean remove(Object obj) {
        Order order = (Order) obj;
        orderList.remove(order);
        return true;
    }


    /**
     *  gets order number of current order
     * @return current order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     *  gets current order
     * @return current order
     */
    public Order getOrder() {
        return order;
    }

    /**
     *  gets orders arraylist
     *  return arraylist of orders
     */
    public ArrayList<Order> getOrderList() {
        return orderList;
    }
}
