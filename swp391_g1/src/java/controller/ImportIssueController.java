/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.IssueDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Function;
import model.Issue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DELL
 */
public class ImportIssueController extends HttpServlet {

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
            IssueDAO issueDAO = new IssueDAO();
            List<Issue> listIssue = issueDAO.listIssueImport();
            System.out.println("--before list--");
            for (Issue i : listIssue) {
                System.out.println(i);
            }
            List<Function> listFunction = issueDAO.listFunction();
            // use fixed file
            String excelFilePath = "D://listIssue5.xlsx";

            // Get file
            FileInputStream file = new FileInputStream(new File(excelFilePath));

            // Get workbook
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get sheet
            XSSFSheet sheet = workbook.getSheetAt(0);

            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = (Row) sheet.getRow(i);

                String issueTitle = row.getCell(0).getStringCellValue();
                String description = row.getCell(1).getStringCellValue();
                String gitlab = row.getCell(2).getStringCellValue();
                int functionID = (int) row.getCell(3).getNumericCellValue();
                String functionName = row.getCell(4).getStringCellValue();
                int status = (int) row.getCell(5).getNumericCellValue();
                int assignID = (int) row.getCell(6).getNumericCellValue();
                String assignName = row.getCell(7).getStringCellValue();
                int teamID = (int) row.getCell(8).getNumericCellValue();
                String teamName = row.getCell(9).getStringCellValue();
                // data
                System.out.println(issueTitle);
                System.out.println(description);
                System.out.println(gitlab);
                System.out.println(functionID);
                System.out.println(functionName);
                System.out.println(status);
                System.out.println(assignID);
                System.out.println(assignName);
                System.out.println(teamID);
                System.out.println(teamName);

                issueDAO.createIssue1(issueTitle, description, gitlab, functionID, status, assignID, teamID);
                listIssue.add(new Issue(issueTitle, description,gitlab,functionID,
                        functionName, status, assignID,assignName,teamID, teamName));
            }
            System.out.println("--after list--");
            for (Issue f : listIssue) {
                System.out.println(f);
            }
            request.setAttribute("listFunction", listFunction);
            request.setAttribute("listIssue", listIssue);
            request.setAttribute("stt", "3");
            request.getRequestDispatcher("issue-list.jsp").forward(request, response);
            workbook.close();
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
