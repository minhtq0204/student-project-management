/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CriteriaDAO;
import dao.IterationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Criteria;
import model.Iteration;
import model.Subject;

/**
 *
 * @author DELL
 */
public class SearchCriteriaController extends HttpServlet {

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
            throws ServletException, IOException, SQLException, Exception {
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
        try {
            // processRequest(request, response);
            // get data from DAO
            CriteriaDAO dao = new CriteriaDAO();
            List<Iteration> listIteration = dao.listIteration();
            List<Subject> listSubject = dao.listSubject();
            String subject = request.getParameter("subjectID");
            String iteration = request.getParameter("iterationID");
            
            System.out.println(iteration + "- " + subject);
            
            List<Criteria> listCriteria = dao.searchCriteria(subject,iteration);
            // set data to jsp
            request.setAttribute("subjectID", subject);
            request.setAttribute("iterationID", iteration);
            request.setAttribute("listSubject", listSubject);
            request.setAttribute("listIteration", listIteration);
            request.setAttribute("listCriteria", listCriteria);
            request.getRequestDispatcher("criteria-list.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchCriteriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ListIterationController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
