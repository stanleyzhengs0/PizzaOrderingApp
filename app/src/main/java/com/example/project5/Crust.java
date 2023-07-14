package com.example.project5;

/**
 This creates a crust object and sets the crust
 @author Stanley Zheng, Jamie Pan
 */
public class Crust {

    String crustType;


    /**
     * creates a crust object of the given type
     * @param crustType the crust type
     */
    public Crust(String crustType) {
        this.crustType = crustType;
    }

    /**
     * gets pizza's crust type
     * @return the crust type
     */
    public String getCrustType() {
        return crustType;
    }
}