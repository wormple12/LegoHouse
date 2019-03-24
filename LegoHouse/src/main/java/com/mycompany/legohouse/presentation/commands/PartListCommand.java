/*
 */
package com.mycompany.legohouse.presentation.commands;

import com.mycompany.legohouse.data.DataAccessException;
import com.mycompany.legohouse.logic.Facade;
import com.mycompany.legohouse.logic.FacadeImpl;
import com.mycompany.legohouse.logic.help_classes.House;
import com.mycompany.legohouse.logic.help_classes.Part;
import com.mycompany.legohouse.presentation.Command;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Simon Asholt Norup
 */
public class PartListCommand extends Command {

    private static final Facade CTRL = new FacadeImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int length, width, height;
            try {
                length = Integer.parseInt(request.getParameter("length"));
                width = Integer.parseInt(request.getParameter("width"));
                height = Integer.parseInt(request.getParameter("height"));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Please enter valid numbers for length, width and height.");
            }

            House house = new House(width, length, height);
            LinkedList<LinkedList<Part>> parts = CTRL.getMaterialBill(house);

            HttpSession session = request.getSession();
            session.setAttribute("parts", parts);
            return "PartList";
        } catch (DataAccessException | IllegalArgumentException e) {
            if (e.getCause() != null) {
                request.setAttribute("error", e.getCause());
            } else {
                request.setAttribute("error", e);
            }
            return "Main";
        }
    }

}
