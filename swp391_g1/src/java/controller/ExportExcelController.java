/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.IssueDAO;
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
import model.Issue;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ExportExcelController", urlPatterns = {"/ExportExcelController"})
public class ExportExcelController extends HttpServlet {

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
            response.setHeader("Content-Disposition", "attachment;filename=issueList.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("listIssue");

            IssueDAO issueDAO = new IssueDAO();
            List<Issue> listIssue = issueDAO.listIssueExport();

            // 1 header
            int rowNo = 0;
            XSSFRow row = sheet.createRow(rowNo++);
            int cellNum = 0;

            XSSFCell cell = row.createCell(cellNum++);
            cell.setCellValue("Issue Title");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Function Name");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Assign Name");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Team Name");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Create At");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Status");


            // 2 details
            for (Issue t : listIssue) {
                cellNum = 0;
                row = sheet.createRow(rowNo++);
                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getIssueTitle());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getFunctionName());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getAssignName());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getTeamName());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getCreateAt());

                cell = row.createCell(cellNum++);
                cell.setCellValue(t.getStatus());

            }

            // end create excel
            wb.write(response.getOutputStream());
            wb.close();
            return;
        } catch (Exception e) {
            Logger.getLogger(ExportExcelController.class.getName()).log(Level.SEVERE, null, e);
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
