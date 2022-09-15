/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Thanh
 */
public class LoginServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null) {
            session.removeAttribute("account");
        }
        String account = request.getParameter("uname");
        System.out.println(account);
        String password = request.getParameter("psw");
        System.out.println(password);
        System.out.printf("account log", account);
        boolean remember = request.getParameter("rememberMe") != null;

        String hashText = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte

            byte[] messageDigest = messageDigest = md.digest(password.getBytes());

            // Convert byte array into signum representation
            BigInteger number = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            hashText = number.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        User ac = new UserDAO().getOneAccount(account, hashText);

        if (ac
                != null) {
            if (remember) {
                Cookie cookieUsername = new Cookie("cookieUsername", account);
                Cookie cookiePassword = new Cookie("cookiePassword", password);
                response.addCookie(cookieUsername);
                response.addCookie(cookiePassword);
            }
            session.setAttribute("account", ac);
            response.sendRedirect("home");
        } else {
            request.setAttribute("message", "Sai tên tài khoản hoặc mật khẩu.");
            request.setAttribute("oldUname", account);
            request.setAttribute("oldPass", password);
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
            Logger.getLogger(LoginServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
