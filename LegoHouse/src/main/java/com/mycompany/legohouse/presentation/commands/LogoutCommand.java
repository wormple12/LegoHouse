package com.mycompany.legohouse.presentation.commands;

import com.mycompany.legohouse.presentation.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Simon Asholt Norup
 */
public class LogoutCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        throw new ServletException("You are now logged out."); // frontcontroller redirects to index.jsp when catching exceptions
    }

}
