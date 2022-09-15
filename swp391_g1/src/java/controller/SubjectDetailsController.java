/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SubjectDAO;
import dao.UserDAO;
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
import model.User;

/**
 *
 * @author DELL
 */
public class SubjectDetailsController extends HttpServlet {

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
            out.println("<title>Servlet SubjectDetailsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectDetailsController at " + request.getContextPath() + "</h1>");
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
            dao.SubjectDAO subjectDAO = new SubjectDAO();
            List<User> listAuthor = subjectDAO.listAuthor();
            request.setAttribute("listAuthor", listAuthor);
            System.out.println(listAuthor.toString());
            request.getRequestDispatcher("subject-details.jsp").forward(request, response);
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
        try {
            PrintWriter out = response.getWriter();
            String subjectCode = request.getParameter("subjectcode");
            String subjectName = request.getParameter("subjectname");
            int authorID = Integer.parseInt(request.getParameter("authorID"));
            int status = Integer.parseInt(request.getParameter("status"));
            String description = request.getParameter("description");
            dao.SubjectDAO subjectDAO = new SubjectDAO();
            List<User> listAuthor = subjectDAO.listAuthor();
            HttpSession session = request.getSession();
            request.setAttribute("listAuthor", listAuthor);

            if (new dao.SubjectDAO().checkDupSubjectCode(subjectCode)) {
                request.setAttribute("errorcode", "Subject code is existed ! Please enter again");
                request.setAttribute("subjectcode", subjectCode);
                request.setAttribute("subjectname", subjectName);
                request.setAttribute("authorID", authorID);
                request.setAttribute("description", description);
                request.setAttribute("status", status);
                request.getRequestDispatcher("subject-details.jsp").forward(request, response);
            } else if (new dao.SubjectDAO().checkDupSubjectName(subjectName)) {
                request.setAttribute("errorname", "Subject name is exitted ! Please enter again");
                request.setAttribute("subjectcode", subjectCode);
                request.setAttribute("subjectname", subjectName);
                request.setAttribute("authorID", authorID);
                request.setAttribute("description", description);
                request.setAttribute("status", status);
                request.getRequestDispatcher("subject-details.jsp").forward(request, response);
            } else {
                if (subjectDAO.createSubject(subjectCode, subjectName, authorID, status, description)) {
                    session.setAttribute("stt", "1");
                    request.getRequestDispatcher("ListSubjectController").forward(request, response);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(SubjectDetailsController.class.getName()).log(Level.SEVERE, null, ex);
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
