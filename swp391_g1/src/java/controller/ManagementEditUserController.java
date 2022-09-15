/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author mac
 */
public class ManagementEditUserController extends HttpServlet {

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
            int userId = Integer.parseInt(request.getParameter("id"));
            UserDAO pDAO = new UserDAO();
            User user = pDAO.getUserById(userId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("edit-user.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        try {
            UserDAO pDAO = new UserDAO();
            System.out.println(request.getParameter("id"));
            int userId = Integer.parseInt(request.getParameter("id"));
            System.out.println(userId + "1");
            String rollNum = request.getParameter("roll_number");
            String fullName = request.getParameter("full_name");
            int gender = Integer.parseInt(request.getParameter("gender"));
            String dob = request.getParameter("dob");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String avaLink = request.getParameter("avatar_link");
            String faceLink = request.getParameter("face_link");
            int roleId = Integer.parseInt(request.getParameter("role_id"));
            int statusId = Integer.parseInt(request.getParameter("status"));
            String password = request.getParameter("password");
            HttpSession session = request.getSession();
            if (pDAO.updateUser(userId, rollNum, fullName, gender, dob, email, mobile, avaLink, faceLink, roleId, statusId)) {
                session.setAttribute("stt", "2");
                response.sendRedirect("UserController");
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
