/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CriteriaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Iteration;
import model.Subject;

/**
 *
 * @author DELL
 */
public class CriteriaDetailsController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

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
        dao.CriteriaDAO criteriaDAO = new CriteriaDAO();
        try {
            List<Subject> listSubject = criteriaDAO.listSubject();
            List<Iteration> listIteration = criteriaDAO.listIteration();
           
            request.setAttribute("listSubject", listSubject);
            request.setAttribute("listIteration", listIteration);
            request.getRequestDispatcher("criteria-details.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (Exception ex) {
            Logger.getLogger(SubjectDetailsController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        int subjectID = Integer.parseInt(request.getParameter("subjectname"));
        int iterationID = Integer.parseInt(request.getParameter("itername"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        String team = request.getParameter("team");
        int order = Integer.parseInt(request.getParameter("criorder"));
        int loc = Integer.parseInt(request.getParameter("loc"));
        int status = Integer.parseInt(request.getParameter("status"));
        String description = request.getParameter("description");
        String criteriaName = request.getParameter("criterianame");
        dao.CriteriaDAO criteriaDAO = new CriteriaDAO();
        HttpSession session = request.getSession();
        try {
            List<Subject> listSubject = criteriaDAO.listSubject();
            List<Iteration> listIteration = criteriaDAO.listIteration();
            request.setAttribute("listSubject", listSubject);
            request.setAttribute("listIteration", listIteration);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (Exception ex) {
            Logger.getLogger(CriteriaDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (new dao.CriteriaDAO().checkDupCriteria(criteriaName)) {
            request.setAttribute("errorname", "Criteria name is existed ! Please choose another");
            request.setAttribute("weight", weight);
            request.setAttribute("team", team);
            request.setAttribute("criorder", order);
            request.setAttribute("loc", loc);
            request.setAttribute("status", status);
            request.getRequestDispatcher("criteria-details.jsp").forward(request, response);
        } else {
            if (criteriaDAO.createCriteria(weight, team, order, loc, status, iterationID, subjectID, description, criteriaName)) {
                session.setAttribute("stt", "1");
                request.getRequestDispatcher("ListCriteriaController").forward(request, response);

            }

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
