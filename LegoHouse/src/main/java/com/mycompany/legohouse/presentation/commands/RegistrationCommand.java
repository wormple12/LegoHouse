package com.mycompany.legohouse.presentation.commands;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.legohouse.data.DataAccessException;
import com.mycompany.legohouse.logic.Facade;
import com.mycompany.legohouse.logic.FacadeImpl;
import com.mycompany.legohouse.logic.help_classes.User;
import com.mycompany.legohouse.presentation.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command class for registering new users. Admin status must be applied manually in SQL database.
 * Retrieves user parameters on input and adds to database.
 * Does not redirect the main menu. User is redirected to login site on creation.
 * Used by Front Controller.
 * @author Emil PC
 */
public class RegistrationCommand extends Command {
    
    private static final Facade CTRL = new FacadeImpl();
    
/**
 * Registration command, needs username, password and email which it then tries to use
 * to create a new user in the database.
 * It then forwards you based on whether the creation was succesful or not.
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException 
 */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        try {
            if (username != null && password != null && email != null) {
                User user = new User(username, password, email);
                CTRL.registerUser(user);
                
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return "Main";
            } else {
                return "Register";
            }
        } catch (DataAccessException|IllegalArgumentException e) {
            if (e.getCause() != null) {
                request.setAttribute("error", e.getCause());
            } else {
                request.setAttribute("error", e);
            }
            return "Register";
        }
    }
}
