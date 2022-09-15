/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.IterationDAO;
import dao.MilestoneDAO;
import dao.TeamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

/**
 *
 * @author minhc
 */
public class AddMilestoneController extends HttpServlet {

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
            TeamDAO teamDao = new TeamDAO();
            IterationDAO idao = new IterationDAO();
            List<model.Class> listClass = teamDao.listClass();
            List<Iteration> iterations = idao.getIterations();
            request.setAttribute("iterations", iterations);
            request.setAttribute("listClass", listClass);
            request.getRequestDispatcher("milestone-add.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddMilestoneController.class.getName()).log(Level.SEVERE, null, ex);
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

        int classID = Integer.parseInt(request.getParameter("classID"));
        String milestoneName = request.getParameter("MileStoneName");
        int iterationID = Integer.parseInt(request.getParameter("iterationID"));
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String description = request.getParameter("description");
        int status = Integer.parseInt(request.getParameter("status"));
        dao.MilestoneDAO mileStoneDAO = new MilestoneDAO();
        try {
           TeamDAO teamDao = new TeamDAO();
            IterationDAO idao = new IterationDAO();
            List<model.Class> listClass = teamDao.listClass();
            List<Iteration> iterations = idao.getIterations();

            request.setAttribute("iterations", iterations);
            request.setAttribute("listClass", listClass);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (Exception ex) {
            Logger.getLogger(AddMilestoneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpSession session = request.getSession();
        if (mileStoneDAO.createMileStone(fromDate, toDate, status, iterationID, classID, milestoneName, description)) {
            session.setAttribute("stt", "1");
            response.sendRedirect("MilestoneListController");
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
