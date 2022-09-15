package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.IssueDAO;
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
import model.Function;
import model.Issue;
import model.Team;
import model.User;

/**
 *
 * @author DELL
 */
public class IssueDetailsController extends HttpServlet {

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
        dao.IssueDAO issueDAO = new IssueDAO();
        try {
            List<Function> listFunction = issueDAO.listFunction();
            List<User> listUser = issueDAO.listUser();
            List<Team> listTeam = issueDAO.listTeam();
            request.setAttribute("listFunction", listFunction);
            request.setAttribute("listUser", listUser);
            request.setAttribute("listTeam", listTeam);
             request.getRequestDispatcher("issue-details.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(IssueDetailsController.class.getName()).log(Level.SEVERE, null, ex);
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
        String issueTitle = request.getParameter("issuetitle");
        int function = Integer.parseInt(request.getParameter("functionname"));
        int assign = Integer.parseInt(request.getParameter("assignname"));
        int team = Integer.parseInt(request.getParameter("teamname"));
        String gitlab = request.getParameter("giturl");
        String dueDate = request.getParameter("duedate");
        String description = request.getParameter("description");
        int status = Integer.parseInt(request.getParameter("status"));
        HttpSession session = request.getSession();
        dao.IssueDAO issueDAO = new IssueDAO();
        try {
            List<Function> listFunction = issueDAO.listFunction();
            List<User> listUser = issueDAO.listUser();
            List<Team> listTeam = issueDAO.listTeam();
            request.setAttribute("listFunction", listFunction);
            request.setAttribute("listUser", listUser);
            request.setAttribute("listTeam", listTeam);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (Exception ex) {
            Logger.getLogger(IssueDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (issueDAO.createIssue(issueTitle, description, dueDate, gitlab, function, status, assign, team)) {
            session.setAttribute("stt", "1");
            request.getRequestDispatcher("ListIssueController").forward(request, response);
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
