/*
 */

package com.mycompany.legohouse.data;

import com.mycompany.legohouse.logic.help_classes.Brick;
import com.mycompany.legohouse.logic.help_classes.Order;
import java.util.ArrayList;

/**
 * @author Simon Asholt Norup
 */
public interface OrderDAO {
    
    public void addOrder(Order order) throws DataAccessException;
    
    public ArrayList<Order> getUserOrders(String username) throws DataAccessException;
    
    public ArrayList<Order> getAllOrders() throws DataAccessException;
    
    public void shipOrder(int id) throws DataAccessException;
    
    public ArrayList<Brick> getValidBrickTypes() throws DataAccessException;

}
