/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClassDAO;
import dao.MilestoneDAO;
import dao.TeamDAO;
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
import javax.servlet.http.HttpSession;
import model.Milestone;

/**
 *
 * @author minhc
 */
public class MilestoneListController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        MilestoneDAO mileStoneDAO = new MilestoneDAO();
        ClassDAO classDAO = new ClassDAO();

        String searchClass = "".equals(request.getParameter("status")) ? "" : request.getParameter("searchBy");
        String searchStatus = "".equals(request.getParameter("classID")) ? "" : request.getParameter("searchBy");
        String indexPage = request.getParameter("index");

        System.out.println(searchClass);
        System.out.println(searchStatus);

        searchClass = "0";
        searchStatus = "3";

        if (indexPage == null) {
            indexPage = "1";
        }
        int countSearch = 0;
        int index = Integer.parseInt(indexPage);
        int count = Integer.parseInt(mileStoneDAO.countMileStone());
        if (count % 5 != 0) {
            count = (count / 5) + 1;
        } else {
            count = count / 5;
        }

        System.out.println(index + " index ");

        List<Milestone> mileStone = mileStoneDAO.searchMileStone(searchStatus, searchClass, index);
        List<model.Class> listClass = mileStoneDAO.listClass();
        
        request.setAttribute("listClass", listClass);
        request.setAttribute("MileStone", mileStone);
        request.setAttribute("countSearch", countSearch);
        request.setAttribute("endPage", count);
        request.getRequestDispatcher("milestone-list.jsp").forward(request, response);
        session.removeAttribute("stt");
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
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(MilestoneListController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MilestoneListController.class.getName()).log(Level.SEVERE, null, ex);
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
