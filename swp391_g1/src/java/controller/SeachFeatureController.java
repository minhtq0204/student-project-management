/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FeatureDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Feature;
import model.Team;
import model.Class;

/**
 *
 * @author minhc
 */
public class SeachFeatureController extends HttpServlet {

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
            // processRequest(request, response);
            // get data from DAO
            FeatureDAO featureDAO = new FeatureDAO();
            List<Team> listTeam = featureDAO.listTeam();
            List<Class> listClass = featureDAO.listClass();
            
            String teamID = request.getParameter("teamID");
            String classID = request.getParameter("classID");
            String status = request.getParameter("status");
            String txtSearch = request.getParameter("txtSearch");
            System.out.println(teamID + "- " + status);
            
            List<Feature> list = featureDAO.searchFeature(status, teamID, classID, txtSearch);
            
            // set data to jsp
            request.setAttribute("teamID", teamID);
            request.setAttribute("classID", classID);
            request.setAttribute("status", status);
            request.setAttribute("txtSearch", txtSearch);
            request.setAttribute("listTeam", listTeam);
            request.setAttribute("listClass", listClass);
            request.setAttribute("listFeature", list);
            
            request.getRequestDispatcher("feature-list.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SeachFeatureController.class.getName()).log(Level.SEVERE, null, ex);
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
