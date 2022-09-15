/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SettingDAO;
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
import model.Setting;
import model.Type;

/**
 *
 * @author mac
 */
@WebServlet(name = "SettingAddController", urlPatterns = {"/addSetting"})
public class SettingAddController extends HttpServlet {

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
            out.println("<title>Servlet SettingAddController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingAddController at " + request.getContextPath() + "</h1>");
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
//        
        dao.SettingDAO settingDAO = new SettingDAO();
        try {
            List<Type> listTypes = settingDAO.listType();
            request.setAttribute("listType", listTypes);
            request.getRequestDispatcher("setting-add.jsp").forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(SettingAddController.class.getName()).log(Level.SEVERE, null, e);
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
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        int typeID = Integer.parseInt(request.getParameter("typeId"));
        String settingValue = request.getParameter("settingValue");
        String settingNote = request.getParameter("settingNote");
        int status = Integer.parseInt(request.getParameter("status"));
        dao.SettingDAO settingDAO = new SettingDAO();
        if (new dao.SettingDAO().checkDup(settingValue)) {
                request.setAttribute("errorcode", "Duplicated setting value! Please enter again");
                request.setAttribute("typeId", typeID);
                request.setAttribute("settingValue", settingValue);
                request.setAttribute("settingNote", settingNote);
                request.setAttribute("status", status);
                request.getRequestDispatcher("setting-add.jsp").forward(request, response);
        }
        else if(settingDAO.createNewSetting(settingValue, settingNote, status, typeID)){
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Add successfully');");
        out.println("location='SettingController';");
        out.println("</script>");
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
