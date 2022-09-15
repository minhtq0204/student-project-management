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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Subject;
import model.User;

/**
 *
 * @author DELL
 */
public class EditSubjectController extends HttpServlet {

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
            out.println("<title>Servlet EditSubjectController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditSubjectController at " + request.getContextPath() + "</h1>");
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
        SubjectDAO subjectDAO = new SubjectDAO();
        List<User> listAuthor;
        try {
            listAuthor = subjectDAO.listAuthor();
            request.setAttribute("listAuthor", listAuthor);
        } catch (Exception ex) {
            Logger.getLogger(EditSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            int userId = Integer.parseInt(request.getParameter("id"));
            Subject subject = subjectDAO.getSubjectByID(userId);
            System.out.println(subject.getAuthorID());
            request.setAttribute("subject", subject);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("edit-subject.jsp").forward(request, response);
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
        try {
            SubjectDAO subjectDAO = new SubjectDAO();
            System.out.println(request.getParameter("id"));
            int userId = Integer.parseInt(request.getParameter("id"));
            System.out.println(userId + "1");
            String subjectCode = request.getParameter("subjectcode");
            String subjectName = request.getParameter("subjectname");
            int authorID = Integer.parseInt(request.getParameter("authorID"));
            int status = Integer.parseInt(request.getParameter("status"));
            String description = request.getParameter("description");
            HttpSession session = request.getSession();
            if (subjectDAO.updateSubject(userId, subjectCode, subjectName, authorID, status, description)) {
                session.setAttribute("stt", "2");
                request.getRequestDispatcher("ListSubjectController").forward(request, response);
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
