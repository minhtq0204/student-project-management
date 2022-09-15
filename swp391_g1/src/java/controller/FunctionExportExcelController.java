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
import model.Function;
import model.Team;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author mac
 */
@WebServlet(name = "FunctionExportExcelController", urlPatterns = {"/exportExcelFunction"})
public class FunctionExportExcelController extends HttpServlet {

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
//            out.println("<title>Servlet FunctionExportExcelController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet FunctionExportExcelController at " + request.getContextPath() + "</h1>");
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
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=functionList.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("listTeam");

            FunctionDAO functionDAO = new FunctionDAO();
            List<Function> listFunction = functionDAO.listFunctionExport();
            
            
            // 1 header
            int rowNo = 0;
            XSSFRow row = sheet.createRow(rowNo++);
            int cellNum = 0;

            XSSFCell cell = row.createCell(cellNum++);
            cell.setCellValue("Function ID");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Function Name");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Access Roles");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Description");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Complexity ID");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Priority");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Status");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Team ID");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Team Name");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Feature ID");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Feature Name");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Owner ID");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Owner Name");

            // 2 details
            for (Function t : listFunction) {
                cellNum = 0;
                row = sheet.createRow(rowNo++);
                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getFunctionID());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getFunctionName());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getAccessRoles());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getDescription());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getComplexityID());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getPriority());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getStatus());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getTeamID());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getTeamName());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getFeatureID());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getFeatureName());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getOwnerID());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getOwnerName());
            }

            // end create excel
            wb.write(response.getOutputStream());
            wb.close();
            return;
        } catch (Exception e) {
            Logger.getLogger(FunctionExportExcelController.class.getName()).log(Level.SEVERE, null, e);
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
