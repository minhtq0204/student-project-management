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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author minhq
 */
public class ManagementUserController extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        userDAO = new UserDAO();
    }

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
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ManagementUserController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ManagementUserController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        HttpSession session = request.getSession();
        UserDAO userDAO = new UserDAO();
        List<User> listUser = new ArrayList<>();

        try {
            int userId = Integer.parseInt(request.getParameter("idDelete"));
            System.out.println(request.getParameter("idDelete") + "1");
            if (userDAO.removeUserByUserId(userId)) {
                String indexPage = request.getParameter("index");
                if (indexPage == null) {
                    indexPage = "1";
                }
                int index = Integer.parseInt(indexPage);
                int count = Integer.parseInt(userDAO.countUser());
                int endPage = count / 5;
                if (count % 5 != 0) {
                    endPage = (count / 5) + 1;
                } else {
                    endPage = count / 5;
                }
                listUser = userDAO.getAllUsers(index);
                request.setAttribute("endPage", endPage);
                request.setAttribute("listUser", listUser);
                request.getRequestDispatcher("user-list.jsp").forward(request, response);
            }
        } catch (Exception e) {
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int count = Integer.parseInt(userDAO.countUser());
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage = (count / 5) + 1;
            } else {
                endPage = count / 5;
            }
            listUser = userDAO.getAllUsers(index);
            request.setAttribute("endPage", endPage);

            //Set data to JSP
            request.setAttribute("listUser", listUser);
            request.getRequestDispatcher("user-list.jsp").forward(request, response);
            session.removeAttribute("stt");
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
            String rollNum = ("".equals(request.getParameter("roll_num"))) ? "" : request.getParameter("roll_num");
            String fullName = ("".equals(request.getParameter("full_name"))) ? "" : request.getParameter("full_name");
            int gender = Integer.parseInt(("".equals(request.getParameter("gender"))) ? "" : request.getParameter("gender"));
            String dob = ("".equals(request.getParameter("dob"))) ? "" : request.getParameter("dob");
            String email = ("".equals(request.getParameter("email"))) ? "" : request.getParameter("email");
            String mobile = ("".equals(request.getParameter("mobile"))) ? "" : request.getParameter("mobile");
            String avaLink = ("".equals(request.getParameter("avatar_link"))) ? "" : request.getParameter("avatar_link");
            String faceLink = ("".equals(request.getParameter("face_link"))) ? "" : request.getParameter("face_link");
            int roleId = Integer.parseInt(("".equals(request.getParameter("role_id"))) ? "" : request.getParameter("role_id"));
            String password = ("".equals(request.getParameter("password"))) ? "" : request.getParameter("password");
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
            UserDAO uDAO = new UserDAO();
            HttpSession session = request.getSession();
            if (new dao.UserDAO().checkDup(email)) {
                request.setAttribute("erroremail", "Duplicated email! Please enter other email");
                request.setAttribute("full_name", fullName);
                request.setAttribute("mobile", mobile);
                request.setAttribute("gender", gender);
                request.setAttribute("password", password);
                request.getRequestDispatcher("add-user.jsp").forward(request, response);
            } else if (uDAO.createNewUserFromAdmin(rollNum, fullName, gender, dob, email, mobile,
                    avaLink, faceLink, roleId, hashText)) {
                session.setAttribute("stt", "1");
                response.sendRedirect("UserController");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
