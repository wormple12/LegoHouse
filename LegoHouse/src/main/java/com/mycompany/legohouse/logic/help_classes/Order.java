/*
 */

package com.mycompany.legohouse.logic.help_classes;

import java.time.LocalDateTime;

/**
 * @author Simon Asholt Norup
 */
public class Order {

    private final int id;
    private final String customer;
    private final House house;
    private final LocalDateTime ordered;
    private final LocalDateTime shipped;
    
    public Order(int id, String customer, House house, LocalDateTime ordered, LocalDateTime shipped){
        if (id < 0 || customer == null || house == null || ordered == null){
            throw new IllegalArgumentException("There was an issue concerning order object initialization. Please contact support.");
        }
        this.id = id;
        this.customer = customer;
        this.house = house;
        this.ordered = ordered;
        this.shipped = shipped;
    }
    
    public Order(String customer, House house, LocalDateTime ordered, LocalDateTime shipped){
        this.id = -1;
        this.customer = customer;
        this.house = house;
        this.ordered = ordered;
        this.shipped = shipped;
    }

    public int getID() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public House getHouse() {
        return house;
    }

    public LocalDateTime getOrdered() {
        return ordered;
    }

    public LocalDateTime getShipped() {
        return shipped;
    }

}
