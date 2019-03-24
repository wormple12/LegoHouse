/*
 */

package com.mycompany.legohouse.logic;

import com.mycompany.legohouse.logic.help_classes.User;
import com.mycompany.legohouse.logic.help_classes.House;
import com.mycompany.legohouse.logic.help_classes.Order;
import com.mycompany.legohouse.data.DataAccessException;
import com.mycompany.legohouse.logic.help_classes.Part;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This interface describes all actions that the system must be able to do, regardless of front-end presentation and back-end implementation.
 * @author Simon Asholt Norup
 */
public interface Facade {
    
    public User getUser(String username, String password) throws DataAccessException;
    
    public void registerUser(User user) throws DataAccessException;
    
    //===========================================
    
    public void createOrder(String username, House house) throws DataAccessException;
    
    public ArrayList<Order> getUserOrders(String username) throws DataAccessException;
    
    public ArrayList<Order> getAllOrders() throws DataAccessException;
 
    public LinkedList<LinkedList<Part>> getMaterialBill(House house) throws DataAccessException;
    
    public void shipOrder(int id) throws DataAccessException;

}
