/*
 */
package com.mycompany.legohouse.presentation;

import com.mycompany.legohouse.presentation.commands.*;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Superclass of all web commands with a static method to return its own
 * children. All requests should be redirected to a command through the front
 * controller. It is always the commands that determine (sometimes using request
 * parameters) what JSP page the client should be forwarded to.
 *
 * @author Simon Asholt Norup
 */
public abstract class Command {

    private static final HashMap<String, Command> ACTIONS = new HashMap<String, Command>() {
        {
            put("registration", new RegistrationCommand());
            put("login", new LoginCommand());
            put("logout", new LogoutCommand());
            put("about", new AboutCommand());
            
            put("main", new MainCommand());
            put("partlist", new PartListCommand());
            put("addorder", new AddOrderCommand());
            put("orders", new OrderListCommand());
        }
    };

    /**
     * Executes the code of the given Command subclass. This finds any relevant
     * request parameters or attributes, communicates with any relevant
     * databases via the CupcakeDAO class, changes any relevant attributes with
     * given information, and forwards the request to a JSP web page.
     *
     * @param request the HTTP request to fetch data from, edit, and/or
     * forward/redirect
     * @param response the HTTP response to forward/redirect
     * @throws ServletException
     * @throws IOException
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    /**
     * Uses the path info (meaning * in the path "/LegoHouse/c/*") to determine
     * what Command child class should be returned. All possible Command options
     * have been defined in the ACTIONS hashmap.
     *
     * @param request the given request from the client, used to determine the
     * path info
     * @return the Command subclass value that has been mapped with the given
     * path key, or the UnknownCommand subclass if the given path isn't defined
     */
    public static Command from(HttpServletRequest request) {
        String path = request.getPathInfo().substring(1); // substrings "/" out of the path
//        String path = request.getParameter("path");

        return ACTIONS.getOrDefault(path, new UnknownCommand());
    }
}
