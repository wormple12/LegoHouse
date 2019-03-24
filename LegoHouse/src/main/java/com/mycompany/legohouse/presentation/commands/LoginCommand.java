/*
 */
package com.mycompany.legohouse.presentation.commands;

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
 * Command class for logging into your account. Necessary redirecting to the
 * main menu. Retrieves username and password parameters from the current
 * session. Grants access to the other pages of the webpage. Used by Front
 * Controller.
 *
 * @author Simon Asholt Norup
 */
public class LoginCommand extends Command {

    private static final Facade CTRL = new FacadeImpl();

    /**
     * Basic login functionality, checks the username and password. Prints out
     * errormessages if either username or password is wrong.
     *
     * @param request
     * @param response
     * @return path to JSP page
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (username != null && password != null) {
                User user = CTRL.getUser(username, password);

                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return "Main";
            } else {
                return "Login";
            }
        } catch (DataAccessException|IllegalArgumentException e) {
            if (e.getCause() != null) {
                request.setAttribute("error", e.getCause());
            } else {
                request.setAttribute("error", e);
            }
            return "Login";
        }
    }

}
