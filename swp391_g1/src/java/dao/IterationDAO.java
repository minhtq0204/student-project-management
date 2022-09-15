/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Connector.SQLServerConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Iteration;
import model.Subject;

/**
 *
 * @author DELL
 */
public class IterationDAO {

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public void CloseConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean createIteration(String iterName, String duration, int status, int subjectid, String description) {
        try {
            String sql = "INSERT INTO iteration (iteration_name,druation,status,subject_id,iteration_description) VALUES (?,?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, iterName);
            stm.setString(2, duration);
            stm.setInt(3, status);
            stm.setInt(4, subjectid);
            stm.setString(5, description);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;

        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());

        }
        return false;
    }

    public ArrayList<Iteration> getAllIteration(int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Iteration> list = new ArrayList<>();
        String sql = "select a.iteration_id, a.iteration_name, a.druation,a.status, "
                + "a.subject_id, b.subject_code, a.iteration_description from"
                + " iteration as a, subject as b where a.subject_id=b.subject_id \n"
                + "ORDER BY iteration_id limit 5 OFFSET " + (index - 1) * 5 + "";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Iteration(rs.getInt("iteration_id"), rs.getString("iteration_name"), rs.getString("druation"), rs.getInt("status"), rs.getInt("subject_id"), rs.getString("subject_code"), rs.getString("iteration_description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String countIteration() {
        String query = "select COUNT(iteration_id) as NumberOfIteration from iteration";
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String number = rs.getString("NumberOfIteration");
                return number;
            }
            rs.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public List<Subject> listSubject() throws SQLException, Exception {
        List<Subject> listSubject = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select * from subject where status = 1";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("subject_id");
                String name = rs.getString("subject_code");
                Subject subject = new Subject(id, name);
                listSubject.add(subject);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listSubject;
    }

    public boolean changeStatusIteration(int id) throws Exception {
        boolean check = false;
        Iteration accCheck = new Iteration();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql1 = "select * from iteration where iteration_id = ?";
            stm = conn.prepareStatement(sql1);
            stm.setObject(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                accCheck = new Iteration(rs.getInt("iteration_id"), rs.getString("iteration_name"), rs.getString("druation"), rs.getInt("status"), rs.getInt("subject_id"), rs.getString("iteration_description"));

            }
            CloseConnection();
        } finally {
            CloseConnection();
        }

        try {
            conn = new SQLServerConnection().getConnection();
            String sqltrue = "UPDATE iteration set status=1 where iteration_id = ?";
            String sqlfalse = "UPDATE iteration set status=0 where iteration_id = ?";
            if (accCheck.getStatus() == 0) {
                stm = conn.prepareStatement(sqltrue);
                stm.setObject(1, id);
                int a = stm.executeUpdate();
                check = a > 0 ? true : false;
                CloseConnection();
            } else if (accCheck.getStatus() == 1) {
                stm = conn.prepareStatement(sqlfalse);
                stm.setObject(1, id);
                int a = stm.executeUpdate();
                check = a > 0 ? true : false;
                CloseConnection();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return check;
    }

    public boolean updateIteration(int iterationID, String iterationName,
            String duration, int status, int subjectID, String description) {
        try {
            String sql = "UPDATE iteration SET  iteration_name = ?, druation = ? ,status = ?, subject_id= ? , iteration_description= ? WHERE iteration_id = ? ";

            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, iterationName);
            stm.setString(2, duration);
            stm.setInt(3, status);
            stm.setInt(4, subjectID);
            stm.setString(5, description);
            stm.setInt(6, iterationID);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Update OK");
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Update fail" + e.getMessage());
        }
        return false;
    }


    public Iteration getIterationByID(int id) {
        try {
            String sql = "select * from iteration where iteration_id=?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Iteration subject = new Iteration(rs.getInt("iteration_id"), rs.getString("iteration_name"),
                        rs.getString("druation"), rs.getInt("status"), rs.getInt("subject_id"), rs.getString("iteration_description"));
                stm.close();
                rs.close();
                return subject;
            }
            stm.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public ArrayList<Iteration> searchIterationByName(String name) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Iteration> list = new ArrayList<>();
        String sql = "select * from iteration where iteration_name like '%" + name + "%'";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Iteration(rs.getInt("iteration_id"), rs.getString("iteration_name"), rs.getString("druation"), rs.getInt("status"), rs.getInt("subject_id"), rs.getString("iteration_description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Iteration> searchIterationByName2(String name) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Iteration> list = new ArrayList<>();
        String sql = "select a.iteration_id, a.iteration_name, a.druation,a.status, a.subject_id, b.subject_code, a.iteration_description from iteration as a, subject as b where a.subject_id=b.subject_id and a.iteration_name like '%" + name + "%'";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Iteration(rs.getInt("iteration_id"), rs.getString("iteration_name"), rs.getString("druation"), rs.getInt("status"), rs.getInt("subject_id"), rs.getString("subject_code"), rs.getString("iteration_description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Iteration> searchIterationBySubjectCode(String name) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Iteration> list = new ArrayList<>();
        String sql = "select a.iteration_id, a.iteration_name, a.druation,a.status, a.subject_id, b.subject_code, a.iteration_description from iteration as a, subject as b where a.subject_id=b.subject_id and b.subject_code like '%" + name + "%'";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Iteration(rs.getInt("iteration_id"), rs.getString("iteration_name"), rs.getString("druation"), rs.getInt("status"), rs.getInt("subject_id"), rs.getString("subject_code"), rs.getString("iteration_description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Iteration> getIterations() throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Iteration> list = new ArrayList<>();
        String sql = "select * from iteration";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Iteration(
                        rs.getInt("iteration_id"),
                        rs.getString("iteration_name"),
                        rs.getString("druation"),
                        rs.getInt("status"),
                        rs.getInt("subject_id"),
                        rs.getString("iteration_description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Iteration> searchIteration(String status, String subjectID, String txtSearch) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<Iteration> list = new ArrayList<>();
        String sql = "select a.iteration_id, a.iteration_name, a.druation,a.status, "
                + "a.subject_id, b.subject_code, a.iteration_description from"
                + " iteration as a, subject as b where a.subject_id=b.subject_id";

        if (!status.equals("2")) {
            sql += " and a.status = '" + status + "'";
        }

        if (!subjectID.equals("0")) {
            sql += " and b.subject_id = '" + subjectID + "' ";
        }

        if (txtSearch != null) {
            sql += " and a.iteration_name like '%" + txtSearch + "%'";
        }

        System.out.println(sql);
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Iteration(rs.getInt("iteration_id"), rs.getString("iteration_name"),
                        rs.getString("druation"), rs.getInt("status"), rs.getInt("subject_id"),
                        rs.getString("subject_code"), rs.getString("iteration_description")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws SQLException, Exception {
        IterationDAO x = new IterationDAO();
        List<Iteration> list = new ArrayList<>();
        list = x.getIterations();
        System.out.println(list);
    }

}
