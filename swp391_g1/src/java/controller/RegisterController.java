/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import dao.UserDAO;
import static java.lang.System.out;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author DELL
 */
public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
       // request.setAttribute("success", null);
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String name = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String password = request.getParameter("pass");

        String repass = request.getParameter("re-pass");
        String genderVal = request.getParameter("gender");
        int gender = Integer.parseInt(genderVal);

        if (new dao.UserDAO().checkDup(email)) {
            request.setAttribute("erroremail", "Duplicated email! Please enter other email");
            request.setAttribute("fullname", name);
            request.setAttribute("phone", phone);
            request.setAttribute("gender", gender);
            request.setAttribute("pass", password);

            request.setAttribute("re-pass", repass);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (password.length() < 6) {
            request.setAttribute("errorpass", "Password must greater than 6 character !");
            request.setAttribute("fullname", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("re-pass", repass);
            request.setAttribute("gender", gender);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (phone.length() < 10 || phone.length() > 10) {
            request.setAttribute("errorphone", "Make sure that phone less than 10 number !");
            request.setAttribute("fullname", name);
            request.setAttribute("email", email);
            request.setAttribute("pass", password);
            request.setAttribute("re-pass", repass);
            request.setAttribute("gender", gender);
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (password.equals(repass) == false) {
            request.setAttribute("errorrepass", "Re-pass does not match with password!Please enter again");
            request.setAttribute("fullname", name);
            request.setAttribute("email", email);
            request.setAttribute("pass", password);
            request.setAttribute("gender", gender);
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            String hashText = null;
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");

                    // digest() method is called to calculate message digest
                //  of an input digest() return array of byte
                byte[] messageDigest = md.digest(password.getBytes());

                // Convert byte array into signum representation
                BigInteger no = new BigInteger(1, messageDigest);

                // Convert message digest into hex value
                hashText = no.toString(16);
                while (hashText.length() < 32) {
                    hashText = "0" + hashText;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
            dao.UserDAO userDAO = new UserDAO();


            // request.getRequestDispatcher("login.jsp").forward(request, response);

           if(userDAO.createNewUser(name, email, hashText, gender, phone)){
                request.setAttribute("stt", 1);
                request.getRequestDispatcher("login.jsp").forward(request, response);
           }
            
           // request.getRequestDispatcher("login.jsp").forward(request, response);

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
