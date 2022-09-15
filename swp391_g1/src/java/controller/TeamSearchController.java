/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SubjectSettingDAO;
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
import model.SubjectSetting;
import model.Team;

/**
 *
 * @author mac
 */
@WebServlet(name = "TeamSearchController", urlPatterns = {"/searchTeam"})
public class TeamSearchController extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TeamSearchByStatusController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeamSearchByStatusController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            // processRequest(request, response);
            // get data from DAO
            TeamDAO dao = new TeamDAO();
            List<model.Class> listClass = dao.listClass();;
          
            String classID = request.getParameter("classID");
            String status = request.getParameter("status");
            String teamName= request.getParameter("teamName");
       
            String txtSearch = request.getParameter("txtSearch");

            System.out.println(classID + "- " + status);

            List<Team> list = dao.searchTeam(status, classID, txtSearch,teamName);

            // set data to jsp
            request.setAttribute("classID", classID);
            request.setAttribute("teamName", teamName);
            request.setAttribute("status", status);
            request.setAttribute("txtSearch", txtSearch);
         
            request.setAttribute("listTeam", list);
            request.setAttribute("listClass", listClass);
            request.getRequestDispatcher("team-list.jsp").forward(request, response);
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
