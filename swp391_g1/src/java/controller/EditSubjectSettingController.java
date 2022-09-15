/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SubjectSettingDAO;
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
import model.Type;

/**
 *
 * @author DELL
 */
public class EditSubjectSettingController extends HttpServlet {

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
            out.println("<title>Servlet EditSubjectSettingController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditSubjectSettingController at " + request.getContextPath() + "</h1>");
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
        dao.SubjectSettingDAO subjectSettingDAO = new SubjectSettingDAO();
        try {
            List<Subject> listSubject = subjectSettingDAO.listSubject();
            List<Type> listType = subjectSettingDAO.listType();
            request.setAttribute("listSubject", listSubject);
            request.setAttribute("listType", listType);
        } catch (Exception ex) {
            Logger.getLogger(EditSubjectSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int sbsettingID = Integer.parseInt(request.getParameter("id"));
            model.SubjectSetting subjectSetting = subjectSettingDAO.getSubjectSettingByID(sbsettingID);
            request.setAttribute("x", subjectSetting);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("subject-setting-detail.jsp").forward(request, response);
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
            dao.SubjectSettingDAO subjectSettingDAO = new SubjectSettingDAO();
            System.out.println(request.getParameter("id"));
            int sbSetting = Integer.parseInt(request.getParameter("id"));
            System.out.println(sbSetting + "1");
            int typeID = Integer.parseInt(request.getParameter("typename"));
            String settingTitle = request.getParameter("settingtitle");
            String settingValue = request.getParameter("settingvalue");
            String description = request.getParameter("description");
            int status = Integer.parseInt(request.getParameter("status"));
            int subjectId = Integer.parseInt(request.getParameter("subjectname"));
            HttpSession session = request.getSession();
            if (subjectSettingDAO.updateSubjectSetting(sbSetting, settingTitle, settingValue, status, subjectId, typeID, description)) {
                session.setAttribute("stt", "2");
                request.getRequestDispatcher("ListSubjectSettingController").forward(request, response);
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
