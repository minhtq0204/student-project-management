/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FunctionDAO;
import dao.TeamDAO;
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
import model.Feature;
import model.Function;
import model.Team;
import model.User;

/**
 *
 * @author mac
 */
@WebServlet(name = "FunctionSearchController", urlPatterns = {"/searchFunction"})
public class FunctionSearchController extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet FunctionSearchController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet FunctionSearchController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        
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
            FunctionDAO functionDAO = new FunctionDAO();
            List<Team> listTeam = functionDAO.listTeam();
            List<Feature> listFeature = functionDAO.listFeature();
            
            String teamID = request.getParameter("teamID");
            String featureID = request.getParameter("featureID");
            String status = request.getParameter("status");
            String txtSearch = request.getParameter("txtSearch");
            System.out.println(teamID + "- " + status);
            
            List<Function> list = functionDAO.searchFunction(status, teamID, featureID, txtSearch);
            
            // set data to jsp
            request.setAttribute("teamID", teamID);
            request.setAttribute("featureID", featureID);
            request.setAttribute("status", status);
            request.setAttribute("txtSearch", txtSearch);
            request.setAttribute("listTeam", listTeam);
            request.setAttribute("listFeature", listFeature);
            request.setAttribute("listFunction", list);
            
            request.getRequestDispatcher("function-list.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
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
