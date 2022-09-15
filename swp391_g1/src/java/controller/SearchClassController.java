/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClassDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Class;
import model.Subject;
import model.User;

/**
 *
 * @author Thanh
 */
@WebServlet(name = "SearchClassController", urlPatterns = {"/SearchClassController"})
public class SearchClassController extends HttpServlet {

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
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // processRequest(request, response);
            // get data from DAO
            ClassDAO dao = new ClassDAO();
            List<Subject> listSubject = dao.listSubject();
            List<User> listTrainer = dao.listTrainer();
            
            String subjectID = request.getParameter("subjectID");
            String trainerID = request.getParameter("trainerID");
            String status = request.getParameter("status");
            
            List<Class> list = dao.searchClass(status,subjectID,trainerID);
            
            // set data to jsp
            request.setAttribute("subjectID", subjectID);
            request.setAttribute("status", status);
            request.setAttribute("trainerID", trainerID);
            
            request.setAttribute("listSubject", listSubject);
            request.setAttribute("listTrainer", listTrainer);
            request.setAttribute("lstClass", list);
            request.getRequestDispatcher("classlist.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
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
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchClassController.class.getName()).log(Level.SEVERE, null, ex);
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
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchClassController.class.getName()).log(Level.SEVERE, null, ex);
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
