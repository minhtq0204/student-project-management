  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CriteriaDAO;
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
import model.Criteria;
import model.Iteration;
import model.Subject;

/**
 *
 * @author DELL
 */
public class EditCriteriaController extends HttpServlet {

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
            out.println("<title>Servlet EditCriteriaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditCriteriaController at " + request.getContextPath() + "</h1>");
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
        dao.CriteriaDAO criteriaDAO = new CriteriaDAO();
        try {
            List<Iteration> listIteration = criteriaDAO.listIteration();
            List<Subject> listSubject = criteriaDAO.listSubject();
            request.setAttribute("listSubject", listSubject);
            request.setAttribute("listIteration", listIteration);

        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (Exception ex) {
            Logger.getLogger(EditCriteriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int criteriaID = Integer.parseInt(request.getParameter("id"));
            Criteria criteria = criteriaDAO.getCriteriaByID(criteriaID);
            request.setAttribute("criteria", criteria);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("edit-criteria.jsp").forward(request, response);
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
            CriteriaDAO cdao = new CriteriaDAO();
            System.out.println(request.getParameter("id"));
            int criteriaID = Integer.parseInt(request.getParameter("id"));
            System.out.println(criteriaID + "1");
            int weight = Integer.parseInt(request.getParameter("weight"));
            String description = request.getParameter("description");
            String criteriaName = request.getParameter("criterianame");
            String team = request.getParameter("team");
            int order = Integer.parseInt(request.getParameter("criorder"));
            int loc = Integer.parseInt(request.getParameter("loc"));
            int status = Integer.parseInt(request.getParameter("status"));
            int iterationID = Integer.parseInt(request.getParameter("itername"));
            int subjectID = Integer.parseInt(request.getParameter("subjectname"));
            HttpSession session = request.getSession();
            if (cdao.updateCriteria(criteriaID, weight, team, order, loc, status, iterationID, subjectID, description, criteriaName)) {
                session.setAttribute("stt", "2");
                request.getRequestDispatcher("ListCriteriaController").forward(request, response);
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
