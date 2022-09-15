/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClassDAO;
import dao.UserDAO;
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
import javax.servlet.http.HttpSession;
import model.Subject;
import model.User;

/**
 *
 * @author Thanh
 */
@WebServlet(name = "AddClassController", urlPatterns = {"/AddClassController"})
public class AddClassController extends HttpServlet {

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
            throws ServletException, IOException, Exception {
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
            dao.ClassDAO classDAO = new ClassDAO();
            List<User> listTrainer = classDAO.listTrainer();
            List<Subject> listSubject = classDAO.listSubject();
            request.setAttribute("listSubject", listSubject);
            request.setAttribute("listTrainer", listTrainer);
            request.getRequestDispatcher("add-class.jsp").forward(request, response);
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
            String classCode = request.getParameter("classCode");
            int subjectID = Integer.parseInt(request.getParameter("subjectID"));
            int trainerID = Integer.parseInt(request.getParameter("trainerID"));
            int classYear = Integer.parseInt(request.getParameter("classYear"));
            String classTerm = request.getParameter("classTerm");
            int block5 = Integer.parseInt(request.getParameter("block5"));
            int status = Integer.parseInt(request.getParameter("status"));

            dao.ClassDAO classDAO = new ClassDAO();
            List<Subject> listSubject = classDAO.listSubject();
            List<User> listTrainer = classDAO.listTrainer();
            HttpSession session = request.getSession();

            if (classDAO.createClass(classCode, classYear, classTerm, block5, status, trainerID, subjectID)) {
                String indexPage = request.getParameter("index");
                if (indexPage == null) {
                    indexPage = "1";
                }
                int index = Integer.parseInt(indexPage);
                ArrayList<model.Class> listClass = classDAO.getAllClass(index);
                int count = Integer.parseInt(classDAO.countClass());
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage = (count / 10) + 1;
                } else {
                    endPage = count / 3;
                }
                request.setAttribute("endPage", endPage);
                request.setAttribute("listSubject", listSubject);
                request.setAttribute("listTrainer", listTrainer);
                request.setAttribute("lstClass", listClass);
                session.setAttribute("stt", "1");
                request.getRequestDispatcher("ClassListController").forward(request, response);
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
