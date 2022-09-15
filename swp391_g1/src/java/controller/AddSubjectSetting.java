/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SubjectSettingDAO;
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
import model.Type;

/**
 *
 * @author ninhc
 */
public class AddSubjectSetting extends HttpServlet {

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
        dao.SubjectSettingDAO subjectSettingDAO = new SubjectSettingDAO();
        try {
            List<Subject> listSubject = subjectSettingDAO.listSubject();
            List<Type> listType = subjectSettingDAO.listType();
            request.setAttribute("listSubject", listSubject);
            request.setAttribute("listType", listType);
            request.getRequestDispatcher("add-subject-setting.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddSubjectSetting.class.getName()).log(Level.SEVERE, null, ex);
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
        int typeID = Integer.parseInt(request.getParameter("typename"));
        String settingTitle = request.getParameter("settingtitle");
        String settingValue = request.getParameter("settingvalue");
        String description = request.getParameter("description");
        int status = Integer.parseInt(request.getParameter("status"));
        int subjectId = Integer.parseInt(request.getParameter("subjectname"));
        SubjectSettingDAO dao = new SubjectSettingDAO();
        HttpSession session = request.getSession();
        try {
            List<Subject> listSubject = dao.listSubject();
            List<Type> listType = dao.listType();
            request.setAttribute("listSubject", listSubject);
            request.setAttribute("listType", listType);
        } 
         catch  (Exception e){
        }
        if (dao.createNewSubjectSetting(settingTitle, settingValue, status, subjectId, typeID,description)){ 
            session.setAttribute("stt", "1");
            request.getRequestDispatcher("ListSubjectSettingController").forward(request, response);
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
