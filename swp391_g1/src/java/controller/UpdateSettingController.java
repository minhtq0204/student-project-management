/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SettingDAO;
import dao.TeamDAO;
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
import model.Setting;
import model.Team;
import model.Type;

/**
 *
 * @author mac
 */
@WebServlet(name = "UpdateSettingController", urlPatterns = {"/updateSetting"})
public class UpdateSettingController extends HttpServlet {

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
            out.println("<title>Servlet UpdateSettingController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSettingController at " + request.getContextPath() + "</h1>");
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
        dao.SettingDAO settingDAO = new SettingDAO();
        try {
            List<Type> listType = settingDAO.listType();
            request.setAttribute("listType", listType);
        } catch (Exception e) {
            Logger.getLogger(TeamAddController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            int settingID = Integer.parseInt(request.getParameter("id"));
            Setting setting = settingDAO.getSettingByID(settingID);
            request.setAttribute("setting", setting);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("setting-edit.jsp").forward(request, response);
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
            SettingDAO settingDAO = new SettingDAO();
            int settingId = Integer.parseInt(request.getParameter("id"));
            System.out.println(settingId);
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            System.out.println(typeId);
            String value = request.getParameter("settingValue");
            System.out.println(value);
            int status = Integer.parseInt(request.getParameter("status"));
            System.out.println(status);
            String description = request.getParameter("settingNote");
            System.out.println(description);
            HttpSession session = request.getSession();

            if (new dao.SettingDAO().checkDupSettingValue(value)) {
                request.setAttribute("errorcode", "Setting value is existed !");
                request.setAttribute("topicCode", value);
                request.setAttribute("topicName", typeId);
                request.getRequestDispatcher("setting-edit.jsp").forward(request, response);
            }
            else{
                if(settingDAO.updateSetting(settingId, value, description, status, typeId)){
                     List<Setting> listSetting = null;
                try {
                    String indexPage = request.getParameter("index");
                    if (indexPage == null) {
                        indexPage = "1";
                    }
                    int index = Integer.parseInt(indexPage);
                    listSetting = settingDAO.getListSetting(index);
                    int count = Integer.parseInt(settingDAO.countSetting());
                    int endPage = count / 10;
                    if (count % 10 != 0) {
                        endPage = (count / 10) + 1;
                    } else {
                        endPage = count / 10;
                    }
                    request.setAttribute("endPage", endPage);
                } catch (Exception ex) {
                    Logger.getLogger(TeamAddController.class.getName()).log(Level.SEVERE, null, ex);
                }
                session.setAttribute("stt", "2");
                request.setAttribute("listSetting", listSetting);
                request.getRequestDispatcher("setting-list.jsp").forward(request, response);
                }
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
