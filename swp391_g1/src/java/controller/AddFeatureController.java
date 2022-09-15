/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FeatureDAO;
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
import model.Team;
import model.Class;

/**
 *
 * @author minhc
 */
public class AddFeatureController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     *
     * //
     * <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
     * /**
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
        FeatureDAO fdao = new FeatureDAO();

        try {
            List<Team> listTeam = fdao.listTeam();
            List<Class> listClass = fdao.listClass();
            request.setAttribute("listTeam", listTeam);
            request.setAttribute("listClass", listClass);
            request.getRequestDispatcher("add-feature.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (Exception ex) {
            Logger.getLogger(AddFeatureController.class.getName()).log(Level.SEVERE, null, ex);
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
            HttpSession session = request.getSession();
            String fname = request.getParameter("FeatureName");
            int teamId = Integer.parseInt(request.getParameter("topicName"));
            int classId = Integer.parseInt(request.getParameter("classCode"));
            int status = Integer.parseInt(request.getParameter("status"));

            FeatureDAO fdao = new FeatureDAO();
            List<Team> listTeam = fdao.listTeam();
            List<Class> listClass = fdao.listClass();
            if (fdao.createFeature(fname, status, teamId, classId)) {
                request.setAttribute("listTeam", listTeam);
                request.setAttribute("listClass", listClass);
                session.setAttribute("stt", "1");
                request.getRequestDispatcher("FeatureList").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(AddFeatureController.class.getName()).log(Level.SEVERE, null, ex);
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
