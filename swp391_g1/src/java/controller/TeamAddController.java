/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.IterationDAO;
import dao.SubjectDAO;
import dao.TeamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Class;
import model.Team;
import model.User;

/**
 *
 * @author mac
 */
@WebServlet(name = "TeamAddController", urlPatterns = {"/addTeam"})
public class TeamAddController extends HttpServlet {

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
            out.println("<title>Servlet TeamAddController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeamAddController at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        dao.TeamDAO teamDAO = new TeamDAO();
        try {
            List<Class> listClass = teamDAO.listClass();
            request.setAttribute("listClass", listClass);
            request.getRequestDispatcher("team-add.jsp").forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(TeamAddController.class.getName()).log(Level.SEVERE, null, e);
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
        PrintWriter out = response.getWriter();
        int classID = Integer.parseInt(request.getParameter("classID"));
        String teamName = request.getParameter("teamName");
        String topicCode = request.getParameter("topicCode");
        String topicName = request.getParameter("topicName");
        String gitURL = request.getParameter("gitURL");
        String description = request.getParameter("description");
        int status = Integer.parseInt(request.getParameter("status"));
        HttpSession session = request.getSession();
        dao.TeamDAO teamDAO = new TeamDAO();
        List<Class> listClass;
        try {
            listClass = teamDAO.listClass();
            request.setAttribute("listClass", listClass);
        } catch (Exception ex) {
            Logger.getLogger(TeamAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (new dao.TeamDAO().checkDupTopicCode(topicCode)) {
            request.setAttribute("errorcode", "Topic code is existed !");
            request.setAttribute("topicCode", topicCode);
            request.setAttribute("topicName", topicName);
            request.getRequestDispatcher("team-add.jsp").forward(request, response);
        } else {
            if (teamDAO.createTeam(teamName, topicCode, topicName, gitURL, description, status, classID)) {
                List<Team> listTeam = null;
                try {
                    String indexPage = request.getParameter("index");
                    if (indexPage == null) {
                        indexPage = "1";
                    }
                    int index = Integer.parseInt(indexPage);
                    listTeam = teamDAO.getAllTeam(index);
                    int count = Integer.parseInt(teamDAO.countTeam());
                    int endPage = count / 3;
                    if (count % 3 != 0) {
                        endPage = (count / 3) + 1;
                    } else {
                        endPage = count / 3;
                    }
                    request.setAttribute("endPage", endPage);
                } catch (SQLException ex) {
                    Logger.getLogger(TeamAddController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(TeamAddController.class.getName()).log(Level.SEVERE, null, ex);
                }
                session.setAttribute("stt", "1");
                request.setAttribute("listTeam", listTeam);
                request.getRequestDispatcher("team-list.jsp").forward(request, response);
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
