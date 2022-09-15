/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SubjectSettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Subject;
import model.SubjectSetting;

/**
 *
 * @author mac
 */
@WebServlet(name = "ChangeSubjectSetting", urlPatterns = {"/changeSubjectSetting"})
public class ChangeSubjectSetting extends HttpServlet {

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
            out.println("<title>Servlet ChangeSubjectSetting</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeSubjectSetting at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
            response.setContentType("text/html;charset=UTF-8");
            int id = Integer.parseInt(request.getParameter("id"));
            SubjectSettingDAO dao = new SubjectSettingDAO();
            List<Subject> listSubject = dao.listSubject();
            System.out.println(dao.changeStatusSubjectSetting(id));
            
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            
            ArrayList<SubjectSetting> listSubjectSetting = dao.getListSubjectSetting(index);
            int count = Integer.parseInt(dao.countSubjectSetting());
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage = (count / 10) + 1;
            } else{
                endPage = count/10;
            }
            request.setAttribute("endPage", endPage);
            request.setAttribute("listSubject", listSubject);
            request.setAttribute("listSubjectSetting", listSubjectSetting);
            request.getRequestDispatcher("subject-setting-list.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ChangeSubjectSetting.class.getName()).log(Level.SEVERE, null, ex);
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
