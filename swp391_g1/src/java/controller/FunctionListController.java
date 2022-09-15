/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FunctionDAO;
import dao.TeamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Feature;
import model.Function;
import model.Team;
import model.User;

/**
 *
 * @author mac
 */
@WebServlet(name = "FunctionListController", urlPatterns = {"/functionList"})
public class FunctionListController extends HttpServlet {

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
        //response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet FunctionListController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet FunctionListController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");

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
        HttpSession session = request.getSession();
        dao.FunctionDAO functionDAO = new FunctionDAO();
        try {
            List<Team> listTeam = functionDAO.listTeam();
            List<Feature> listFeature = functionDAO.listFeature();
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            List<Function> listFunction = functionDAO.getAllFunctions(index);
            int count = Integer.parseInt(functionDAO.countFunction());
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage = (count / 5) + 1;
            } else {
                endPage = count / 5;
            }
            request.setAttribute("endPage", endPage);
            request.setAttribute("listTeam", listTeam);
            request.setAttribute("listFeature", listFeature);
            request.setAttribute("listFunction", listFunction);
            request.getRequestDispatcher("function-list.jsp").forward(request, response);
            session.removeAttribute("stt");
        } catch (Exception e) {
            Logger.getLogger(FunctionListController.class.getName()).log(Level.SEVERE, null, e);
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
        processRequest(request, response);
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
