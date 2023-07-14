package com.example.project5;

/**
 This class holds methods to create each option of pizza available
 @author Stanley Zheng, Jamie Pan
 */
public interface PizzaFactory {
    Pizza createDeluxe();
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();
}
