/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.IssueDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class EditIssueController extends HttpServlet {

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
            out.println("<title>Servlet EditIssueController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditIssueController at " + request.getContextPath() + "</h1>");
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
        dao.IssueDAO issueDAO = new IssueDAO();
        try {
            List<Function> listFunction = issueDAO.listFunction();
            List<User> listUser = issueDAO.listUser();
            List<Team> listTeam = issueDAO.listTeam();
            request.setAttribute("listFunction", listFunction);
            request.setAttribute("listUser", listUser);
            request.setAttribute("listTeam", listTeam);
        } catch (Exception ex) {
            Logger.getLogger(EditIssueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int issueID = Integer.parseInt(request.getParameter("id"));
            Issue issue = issueDAO.getIssueID(issueID);
            request.setAttribute("issue", issue);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("edit-issue.jsp").forward(request, response);
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
        IssueDAO issueDAO = new IssueDAO();
        System.out.println(request.getParameter("id"));
        int issueID = Integer.parseInt(request.getParameter("id"));
            System.out.println(issueID + "1");
        String issueTitle = request.getParameter("issuetitle");
        int function = Integer.parseInt(request.getParameter("functionname"));
        int assign = Integer.parseInt(request.getParameter("assignname"));
        int team = Integer.parseInt(request.getParameter("teamname"));
        String gitlab = request.getParameter("giturl");
        String dueDate = request.getParameter("duedate");
        String description = request.getParameter("description");
        int status = Integer.parseInt(request.getParameter("status"));
        HttpSession session = request.getSession();
        if (issueDAO.updateIssue(issueID, issueTitle, description, dueDate, gitlab, function, status, assign, team)) {
            session.setAttribute("stt", "2");
            request.getRequestDispatcher("ListIssueController").forward(request, response);
        }
         } catch (Exception e) {
            e.printStackTrace();
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
