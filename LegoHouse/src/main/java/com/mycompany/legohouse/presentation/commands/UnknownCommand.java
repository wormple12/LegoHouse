/*
 */

package com.mycompany.legohouse.presentation.commands;

import com.mycompany.legohouse.presentation.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for redirecting to a .jsp describing how a user is trying to access a page that does not exist.
 * @author Simon Asholt Norup
 */
public class UnknownCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "PageNotFound";
    }
 
}
