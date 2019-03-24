/*
 */
package com.mycompany.legohouse.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles all major requests from the browser, finding the appropriate Command class and executing them.
 * The Command classes will then handle the logic and redirect to any relevant web pages.
 * The front controller forces all Commands to use the urlPattern "/Cupcake/c/*" where * is any valid String used by the Command parent class to redirect to any of its subclasses.
 * @author Simon Asholt Norup
 */
@WebServlet(name = "FrontController", urlPatterns = {"/c/*"})
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            Command command = Command.from(request);
            String path = command.execute(request, response);
            request.getRequestDispatcher( "/WEB-INF/" + path + ".jsp" ).forward( request, response );
        } catch (IOException | ServletException e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher( "/WEB-INF/Login.jsp" ).forward( request, response );
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "This is a front controller servlet redirecting all requests to the appropiate Command class and executing that command.";
    }// </editor-fold>

}
