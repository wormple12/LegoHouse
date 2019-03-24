/*
 */

package com.mycompany.legohouse.presentation.commands;

import com.mycompany.legohouse.data.DataAccessException;
import com.mycompany.legohouse.logic.Facade;
import com.mycompany.legohouse.logic.FacadeImpl;
import com.mycompany.legohouse.logic.help_classes.Order;
import com.mycompany.legohouse.logic.help_classes.User;
import com.mycompany.legohouse.presentation.Command;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Simon Asholt Norup
 */
public class OrderListCommand extends Command {
    
    private static final Facade CTRL = new FacadeImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id_str = request.getParameter("ship_id");
            if (id_str != null && !id_str.isEmpty()){
                int id = Integer.parseInt(id_str);
                CTRL.shipOrder(id);
            }
            
            User user = (User) request.getSession().getAttribute("user");
            ArrayList<Order> orders;
            if (user.getAdmin()){
                orders = CTRL.getAllOrders();
            } else {
                orders = CTRL.getUserOrders(user.getUsername());
            }
            request.setAttribute("orders", orders);
                    
            return "OrderList";
        } catch (DataAccessException|IllegalArgumentException e) {
            if (e.getCause() != null) {
                request.setAttribute("error", e.getCause());
            } else {
                request.setAttribute("error", e);
            }
            return "Main";
        }
    }
 
}
