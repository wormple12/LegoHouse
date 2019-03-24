
package com.mycompany.legohouse.presentation.commands;

import com.mycompany.legohouse.data.DataAccessException;
import com.mycompany.legohouse.logic.Facade;
import com.mycompany.legohouse.logic.FacadeImpl;
import com.mycompany.legohouse.logic.help_classes.House;
import com.mycompany.legohouse.logic.help_classes.User;
import com.mycompany.legohouse.presentation.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Simon Asholt Norup
 */
public class AddOrderCommand extends Command {
    
    private static final Facade CTRL = new FacadeImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int height = Integer.parseInt(request.getParameter("height"));
        
        try {
            House house = new House(width, length, height);
            
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            CTRL.createOrder(user.getUsername(), house);
            
            return "PartList";
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
