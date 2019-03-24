/*
 */
package com.mycompany.legohouse.logic;

import com.mycompany.legohouse.data.SQL_Impl.UserDAO_SQL;
import com.mycompany.legohouse.data.SQL_Impl.OrderDAO_SQL;
import com.mycompany.legohouse.logic.help_classes.User;
import com.mycompany.legohouse.logic.help_classes.House;
import com.mycompany.legohouse.logic.help_classes.Order;
import com.mycompany.legohouse.data.*;
import com.mycompany.legohouse.logic.help_classes.Part;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Simon Asholt Norup
 */
public class FacadeImpl implements Facade {

    private final UserDAO userDAO;
    private final OrderDAO orderDAO;

    public FacadeImpl() {
        userDAO = new UserDAO_SQL();
        orderDAO = new OrderDAO_SQL();
    }

    @Override
    public User getUser(String username, String password) throws DataAccessException {
        return userDAO.getUser(username, password);
    }

    @Override
    public void registerUser(User user) throws DataAccessException {
        userDAO.registerUser(user);
    }

    //============================================================================
    //============================================================================
    @Override
    public void createOrder(String username, House house) throws DataAccessException {
        Order order = new Order(username, house, LocalDateTime.now(), null);
        orderDAO.addOrder(order);
    }

    @Override
    public ArrayList<Order> getUserOrders(String username) throws DataAccessException {
        return orderDAO.getUserOrders(username);
    }

    @Override
    public ArrayList<Order> getAllOrders() throws DataAccessException {
        return orderDAO.getAllOrders();
    }

    @Override
    public LinkedList<LinkedList<Part>> getMaterialBill(House house) throws DataAccessException {
        LinkedList<LinkedList<Part>> partlists = new LinkedList<>();
        PartsCalculator partsCalc = new PartsCalculator();
        
        for (int i = 1; i <= house.getHeight(); i++) {
            String calcMethod;
            if (i % 2 > 0){
                calcMethod = "normal";
            } else {
                calcMethod = "switch";
            }
            LinkedList<Part> partlist = partsCalc.getParts(house, calcMethod);
            partlists.add(partlist);
        }
        return partlists;
    }

    @Override
    public void shipOrder(int id) throws DataAccessException {
        orderDAO.shipOrder(id);
    }

}
