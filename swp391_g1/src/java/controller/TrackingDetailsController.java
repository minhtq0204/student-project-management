/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TrackingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Function;
import model.Milestone;
import model.Team;
import model.User;

/**
 *
 * @author DELL
 */
public class TrackingDetailsController extends HttpServlet {

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
            out.println("<title>Servlet TrackingDetailsController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TrackingDetailsController at " + request.getContextPath() + "</h1>");
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
        dao.TrackingDAO trackingDAO = new TrackingDAO();
        try {
            List<User> listAssigner = trackingDAO.listAssigner();
            List<User> listAssignee = trackingDAO.listAssignee();
            List<Team> listTeam = trackingDAO.listTeam();
            List<Function> listFunction = trackingDAO.listFunction();
            List<Milestone> listMilestone = trackingDAO.listMilestone();
            
            request.setAttribute("listAssigner", listAssigner);
            request.setAttribute("listAssignee", listAssignee);
            request.setAttribute("listTeam", listTeam);
            request.setAttribute("listFunction", listFunction);
            request.setAttribute("listMilestone", listMilestone);
            request.getRequestDispatcher("tracking-details.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(TrackingDetailsController.class.getName()).log(Level.SEVERE, null, ex);
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
        int assignerID = Integer.parseInt(request.getParameter("assignername"));
        int assigneeID = Integer.parseInt(request.getParameter("assigneename"));
        int teamID = Integer.parseInt(request.getParameter("teamname"));
        int functionID = Integer.parseInt(request.getParameter("functionname"));
        int milestoneID = Integer.parseInt(request.getParameter("milestonename"));
        String trackingNote = request.getParameter("trackingnote");
        String update = request.getParameter("updates");
        int status = Integer.parseInt(request.getParameter("status"));
        String description = request.getParameter("description");
        dao.TrackingDAO trackingDAO = new TrackingDAO();
        try {
            List<User> listAssigner = trackingDAO.listAssigner();
            List<User> listAssignee = trackingDAO.listAssignee();
            List<Team> listTeam = trackingDAO.listTeam();
            List<Function> listFunction = trackingDAO.listFunction();
            List<Milestone> listMilestone = trackingDAO.listMilestone();
            
            request.setAttribute("listAssigner", listAssigner);
            request.setAttribute("listAssignee", listAssignee);
            request.setAttribute("listTeam", listTeam);
            request.setAttribute("listFunction", listFunction);
            request.setAttribute("listMilestone", listMilestone);
        } catch (Exception e) {
        }
        if(trackingDAO.createTracking(assignerID, assigneeID, status, trackingNote, update, teamID, functionID, milestoneID, description)){
            request.setAttribute("stt", "1");
            request.getRequestDispatcher("TrackingListController").forward(request, response);
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
