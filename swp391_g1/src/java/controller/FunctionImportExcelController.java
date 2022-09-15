/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FunctionDAO;
import dao.TeamDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Feature;
import model.Function;
import model.Team;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author mac
 *
 */
@WebServlet(name = "FunctionImportExcelController", urlPatterns = {"/importExcelFunction"})
public class FunctionImportExcelController extends HttpServlet {

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
//            out.println("<title>Servlet FunctionImportExcelController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet FunctionImportExcelController at " + request.getContextPath() + "</h1>");
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
            FunctionDAO dao = new FunctionDAO();
            List<Function> listFunction = dao.listFunctionImport();
            System.out.println("--before list--");
            for (Function f : listFunction) {
                System.out.println(f);
            }
            List<Team> listTeam = dao.listTeam();
            List<Feature> listFeature = dao.listFeature();
            // use fixed file
            String excelFilePath = "/Users/mac/Desktop/function-list.xlsx";

            // Get file
            FileInputStream file = new FileInputStream(new File(excelFilePath));

            // Get workbook
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get sheet
            XSSFSheet sheet = workbook.getSheetAt(0);

            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = (Row) sheet.getRow(i);

                String functionName = row.getCell(0).getStringCellValue();
                String accessRoles = row.getCell(1).getStringCellValue();
                String description = row.getCell(2).getStringCellValue();
                int complexityId = (int) row.getCell(3).getNumericCellValue();
                int priority = (int) row.getCell(4).getNumericCellValue();
                int status = (int) row.getCell(5).getNumericCellValue();
                int teamId = (int) row.getCell(6).getNumericCellValue();
                String teamName = row.getCell(7).getStringCellValue();
                int featureId = (int) row.getCell(8).getNumericCellValue();
                String featureName = row.getCell(9).getStringCellValue();
                int ownerId = (int) row.getCell(10).getNumericCellValue();
                String ownerName = row.getCell(11).getStringCellValue();

                // data
                System.out.println(functionName);
                System.out.println(accessRoles);
                System.out.println(description);
                System.out.println(complexityId);
                System.out.println(priority);
                System.out.println(status);
                System.out.println(teamId);
                System.out.println(teamName);
                System.out.println(featureId);
                System.out.println(featureName);
                System.out.println(ownerId);
                System.out.println(ownerName);
                
                dao.createFunction(functionName, accessRoles, description, complexityId, priority, status, teamId, featureId, ownerId);
                listFunction.add(new Function(functionName, accessRoles, description, complexityId,
                        priority, status, teamId, teamName, featureId, featureName, ownerId, ownerName));
            }
            System.out.println("--after list--");
            for (Function f : listFunction) {
                System.out.println(f);
            }
            request.setAttribute("listTeam", listTeam);
            request.setAttribute("listFeature", listFeature);
            request.setAttribute("listFunction", listFunction);
            request.getRequestDispatcher("function-list.jsp").forward(request, response);
            workbook.close();
            return;
        } catch (Exception e) {
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
