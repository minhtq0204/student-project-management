/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FunctionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "FunctionEditController", urlPatterns = {"/editFunction"})
public class FunctionEditController extends HttpServlet {

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
            out.println("<title>Servlet FunctionEditController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FunctionEditController at " + request.getContextPath() + "</h1>");
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
        try {
            FunctionDAO functionDAO = new FunctionDAO();
            List<Team> listTeam = functionDAO.listTeam();
            List<Feature> listFeature = functionDAO.listFeature();
            List<User> listOwner = functionDAO.listOwner();

            int functionID = Integer.parseInt(request.getParameter("functionID"));
            System.out.println(functionID);
            Function function = functionDAO.getFunctionByID(functionID);
            System.out.println(function.getFunctionID());
            request.setAttribute("function", function);
            
            
            
            request.setAttribute("listTeam", listTeam);
            request.setAttribute("listFeature", listFeature);
            request.setAttribute("listOwner", listOwner);
            request.getRequestDispatcher("function-edit.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FunctionEditController.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.println(request.getParameter("functionID"));
            int functionID = Integer.parseInt(request.getParameter("functionID"));
            int teamID = Integer.parseInt(request.getParameter("teamID"));
            int featureID = Integer.parseInt(request.getParameter("featureID"));
            int ownerID = Integer.parseInt(request.getParameter("ownerID"));
            int complexityID = Integer.parseInt(request.getParameter("complexityID"));
            int priority = Integer.parseInt(request.getParameter("priority"));
            int status = Integer.parseInt(request.getParameter("status"));
            String functionName = request.getParameter("functionName");
            String accessRoles = request.getParameter("accessRoles");
            String description = request.getParameter("description");
            HttpSession session = request.getSession();

            FunctionDAO dao = new FunctionDAO();

            List<Team> listTeam = dao.listTeam();
            List<Feature> listFeature = dao.listFeature();
            List<User> listOwner = dao.listOwner();

            if (dao.updateFunction(functionID, functionName, accessRoles, description,
                    complexityID, priority, status, teamID, featureID, ownerID)) {
                System.out.println(functionID);
                System.out.println(complexityID);
                List<Function> listFunction = null;
                try {
                    String indexPage = request.getParameter("index");
                    if (indexPage == null) {
                        indexPage = "1";
                    }
                    int index = Integer.parseInt(indexPage);
                    listFunction = dao.getAllFunctions(index);
                    int count = Integer.parseInt(dao.countFunction());
                    int endPage = count / 3;
                    if (count % 3 != 0) {
                        endPage = (count / 3) + 1;
                    } else {
                        endPage = count / 3;
                    }
                    request.setAttribute("endPage", endPage);
                } catch (SQLException ex) {
                    Logger.getLogger(FunctionEditController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(FunctionEditController.class.getName()).log(Level.SEVERE, null, ex);
                }
                session.setAttribute("stt", "2");
                request.setAttribute("listFunction", listFunction);
                request.setAttribute("listTeam", listTeam);
                request.setAttribute("listFeature", listFeature);
                request.setAttribute("listOwner", listOwner);
                request.getRequestDispatcher("function-list.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(FunctionEditController.class.getName()).log(Level.SEVERE, null, ex);
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
