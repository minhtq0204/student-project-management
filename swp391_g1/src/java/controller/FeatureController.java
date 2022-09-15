/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FeatureDAO;
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
import model.Feature;
import model.Team;
import model.Class;

/**
 *
 * @author minhc
 */
public class FeatureController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        FeatureDAO fdao = new FeatureDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Feature f = fdao.getFeatureID(id);
        try {
            List<Team> listTeam = fdao.listTeam();
            List<Class> listClass = fdao.listClass();
            request.setAttribute("f", f);
            request.setAttribute("listTeam", listTeam);
            request.setAttribute("listClass", listClass);
        } catch (Exception ex) {
            Logger.getLogger(FeatureController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("EditFeature.jsp").forward(request, response);
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
            FeatureDAO fdao = new FeatureDAO();
            System.out.println(request.getParameter("id"));
            int featureID = Integer.parseInt(request.getParameter("id"));
            System.out.println(featureID + "1");
            String fname = request.getParameter("FeatureName");
            int teamId = Integer.parseInt(request.getParameter("teamId"));
            int classId = Integer.parseInt(request.getParameter("classCode"));
            int status = Integer.parseInt(request.getParameter("status"));
            HttpSession session = request.getSession();
            if (fdao.updateFeature(featureID, fname, status, teamId, classId)) {
                session.setAttribute("stt", "2");
                response.sendRedirect("FeatureList");
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
