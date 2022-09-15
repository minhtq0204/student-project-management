/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Subject;
import model.User;

/**
 *
 * @author mac
 */
@WebServlet(name = "SubjectSearchController", urlPatterns = {"/searchSubject"})
public class SubjectSearchController extends HttpServlet {

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
        try {
            // processRequest(request, response);
            // get data from DAO
            SubjectDAO dao = new SubjectDAO();
            List<User> listAuthor = dao.listAuthor();
            String authorID = request.getParameter("authorID");
            String status = request.getParameter("status");
            String txtSearch = request.getParameter("txtSearch");
            System.out.println(authorID + "- " + status);

            List<Subject> list = dao.searchSubject(status,authorID,txtSearch);

            // set data to jsp
            request.setAttribute("authorID", authorID);
            request.setAttribute("status", status);
            request.setAttribute("txtSearch", txtSearch);
            request.setAttribute("lstSubject", list);
            request.setAttribute("listAuthor", listAuthor);
            request.getRequestDispatcher("subjectlist.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SubjectSearchController.class.getName()).log(Level.SEVERE, null, ex);
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
        return "Short description";
    }// </editor-fold>

}
