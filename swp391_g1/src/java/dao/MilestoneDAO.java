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
import model.Milestone;
import model.Subject;

/**
 *
 * @author minhc
 */
public class MilestoneDAO {

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
    
        public List<model.Class> listClass() throws SQLException, Exception {
        List<model.Class> listClass = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select class_id,class_code\n"
                    + "from class\n"
                    + "where class.status='1'";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("class_id");
                String name = rs.getString("class_code");
                model.Class class1 = new model.Class();
                class1.setClassId(id);
                class1.setClassCode(name);
                listClass.add(class1);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listClass;
    }

   
     public String countMileStone() {
        String sql = "select COUNT(milestone_id) as NumberOfFeature from milestone";
        try {

            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String number = rs.getString("NumberOfFeature");
                return number;
            }
        } catch (Exception ex) {
            Logger.getLogger(FeatureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Milestone> searchMileStone(String status, String classID ,int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<Milestone> list = new ArrayList<>();
        String sql = "select milestone_id,milestone.status,milestone.milestone_name,milestone.description,milestone.iteration_id,milestone.class_id,frome_date,to_date,iteration.iteration_name,class.class_code from milestone\n"
                + "inner join class on milestone.class_id = class.class_id\n"
                + "inner join iteration on milestone.iteration_id = iteration.iteration_id";

        if (!status.equals("3")) {
            sql += " and milestone.status = '" + status + "'";
        }

        if (!classID.equals("0")) {
            sql += " and milestone.class_id = '" + classID + "' ";
        }
        sql += " Order by milestone_id LIMIT 5 OFFSET " + (index - 1) * 5 + "";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Milestone(rs.getInt("milestone_id"), rs.getInt("status"), rs.getInt("iteration_id"), rs.getInt("class_id"), rs.getString("frome_date"), rs.getString("to_date"), rs.getString("iteration_name"), rs.getString("class_code"), rs.getString("milestone_name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Milestone> searchMultiMileStone(String status, String classID ,int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<Milestone> list = new ArrayList<>();
        String sql = "select milestone_id,milestone.status,milestone.milestone_name,milestone.description,milestone.iteration_id,milestone.class_id,frome_date,to_date,iteration.iteration_name,class.class_code from milestone\n"
                + "inner join class on milestone.class_id = class.class_id\n"
                + "inner join iteration on milestone.iteration_id = iteration.iteration_id";

        if (!status.equals("3")) {
            sql += " and milestone.status = '" + status + "'";
        }

        if (!classID.equals("0")) {
            sql += " and milestone.class_id = '" + classID + "' ";
        }
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Milestone(rs.getInt("milestone_id"), rs.getInt("status"), rs.getInt("iteration_id"), rs.getInt("class_id"), rs.getString("frome_date"), rs.getString("to_date"), rs.getString("iteration_name"), rs.getString("class_code"), rs.getString("milestone_name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Milestone> getAllMileStone() throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Milestone> list = new ArrayList<>();
        String sql = "select milestone_id,milestone.status,milestone.milestone_name,milestone.description,milestone.iteration_id,milestone.class_id,frome_date,to_date,iteration.iteration_name,class.class_code from milestone\n"
                + "inner join class on milestone.class_id = class.class_id\n"
                + "inner join iteration on milestone.iteration_id = iteration.iteration_id";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Milestone(rs.getInt("milestone_id"), rs.getInt("status"), rs.getInt("iteration_id"), rs.getInt("class_id"), rs.getString("frome_date"), rs.getString("to_date"), rs.getString("iteration_name"), rs.getString("class_code"), rs.getString("milestone_name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Milestone getMileStonebyID(int milestoneID) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        String sql = "select milestone_id,milestone.status,milestone.milestone_name,milestone.description,milestone.iteration_id,milestone.class_id,frome_date,to_date,iteration.iteration_name,class.class_code from milestone\n"
                + "inner join class on milestone.class_id = class.class_id\n"
                + "inner join iteration on milestone.iteration_id = iteration.iteration_id where milestone_id = "+milestoneID+"  ";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Milestone mile =  new Milestone(rs.getInt("milestone_id"), rs.getInt("status"), rs.getInt("iteration_id"), rs.getInt("class_id"), rs.getString("frome_date"), rs.getString("to_date"), rs.getString("iteration_name"), rs.getString("class_code"), rs.getString("milestone_name"), rs.getString("description"));
                return mile;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public boolean updateMileStone(int milestoneID,String fromDate , String toDate , int status , int iterationID , int classID , String milestoneName , String description ) {

        try {
            String sql = "UPDATE milestone SET frome_date = ?, to_date = ?, status = ?,"
                    + " iteration_id = ?, class_id = ? ,milestone_name = ?, description = ?"
                    + " WHERE milestone_id = ? ";

            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);

            stm.setString(1, fromDate);
            stm.setString(2, toDate);
            stm.setInt(3, status);
            stm.setInt(4, iterationID);
            stm.setInt(5, classID);
            stm.setString(6, milestoneName);
            stm.setString(7, description);
            stm.setInt(8, milestoneID);
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
    
     public boolean createMileStone(String fromdate , String toDate , int status , int iterationID , int classID , String milestoneName , String description) {
        try {
            String sql = "INSERT INTO milestone (frome_date,to_date,status,iteration_id, class_id, milestone_name, description) VALUES (?,?,?,?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
             stm.setString(1, fromdate);
            stm.setString(2, toDate);
            stm.setInt(3, status);
            stm.setInt(4, iterationID);
            stm.setInt(5, classID);
            stm.setString(6, milestoneName);
            stm.setString(7, description);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;

        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());

        }
        return false;
    }
    public static void main(String[] args) throws SQLException, Exception {
        MilestoneDAO md = new MilestoneDAO();
        List<Milestone> list = md.searchMileStone("", "", 3);
        for(Milestone m : list){
            System.out.println(m);
        }
        
    }
}
