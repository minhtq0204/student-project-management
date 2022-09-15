/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.IterationDAO;
import dao.SubjectDAO;
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
import model.Subject;

/**
 *
 * @author DELL
 */
public class IterationDetailsController extends HttpServlet {

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
            out.println("<title>Servlet IterationDetailsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IterationDetailsController at " + request.getContextPath() + "</h1>");
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
        dao.IterationDAO iterationDAO = new IterationDAO();
        try {
            List<Subject> listSubject = iterationDAO.listSubject();
            request.setAttribute("listSubject", listSubject);
            request.getRequestDispatcher("iteration-details.jsp").forward(request, response);
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
        PrintWriter out = response.getWriter();
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        String iterationName = request.getParameter("itername");
        String iterDate = request.getParameter("iterdate");
        int status = Integer.parseInt(request.getParameter("status"));
        String description = request.getParameter("description");
        dao.IterationDAO iterationDAO = new IterationDAO();
        HttpSession session = request.getSession();
        try {
            List<Subject> listSubject = iterationDAO.listSubject();
            request.setAttribute("listSubject", listSubject);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (Exception ex) {
            Logger.getLogger(SubjectDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (iterationDAO.createIteration(iterationName, iterDate, status, subjectID, description)) {
            session.setAttribute("stt", "1");
            request.getRequestDispatcher("ListIterationController").forward(request, response);
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
