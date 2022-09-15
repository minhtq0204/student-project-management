/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Connector.SQLServerConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Criteria;
import model.Iteration;
import model.Subject;

/**
 *
 * @author DELL
 */
public class CriteriaDAO {

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

    public ArrayList<Criteria> getAllCriteria(int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Criteria> listCriteria = new ArrayList<>();
        String sql = "select criteria_id, evaluation_weight, team_evaluation,criteria_order,max_loc,evaluation_criteria.status,\n"
                + "iteration.iteration_name,subject.subject_code,description,criteria_name\n"
                + "from evaluation_criteria\n"
                + "inner join iteration on evaluation_criteria.iteration_id = iteration.iteration_id\n"
                + "inner join subject on evaluation_criteria.subject_id = subject.subject_id\n"
                + "ORDER BY criteria_id limit 5 OFFSET " + (index - 1) * 5 + "";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                listCriteria.add(new Criteria(rs.getInt("criteria_id"), rs.getInt("evaluation_weight"), rs.getString("team_evaluation"), rs.getInt("criteria_order"), rs.getInt("max_loc"), rs.getInt("status"), rs.getString("iteration_name"), rs.getString("subject_code"), rs.getString("description"), rs.getString("criteria_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCriteria;
    }

    public List<Iteration> listIteration() throws SQLException, Exception {
        List<Iteration> listIteration = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select iteration_id,iteration_name\n"
                    + "from iteration\n"
                    + "where iteration.status='1';";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("iteration_id");
                String name = rs.getString("iteration_name");
                Iteration iteration = new Iteration(id, name);
                listIteration.add(iteration);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return listIteration;
    }

    public List<Subject> listSubject() throws SQLException, Exception {
        List<Subject> listSubject = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select subject_id,subject_code\n"
                    + "from subject\n"
                    + "where subject.status='1'";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("subject_id");
                String name = rs.getString("subject_code");
                Subject subject = new Subject();
                subject.setSubjectID(id);
                subject.setSubjectCode(name);
                System.out.println(subject.toString());
                listSubject.add(subject);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listSubject;
    }

    public boolean createCriteria(int evaluationWeight, String teamEvaluation, int criteriaOrder, int max_lox, int status, int iterationID, int subjectID, String description, String criteria_name) {
        try {
            String sql = "INSERT INTO evaluation_criteria (evaluation_weight,team_evaluation,criteria_order,max_loc,status,iteration_id,subject_id,description,criteria_name) VALUES (?,?,?,?,?,?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, evaluationWeight);
            stm.setString(2, teamEvaluation);
            stm.setInt(3, criteriaOrder);
            stm.setInt(4, max_lox);
            stm.setInt(5, status);
            stm.setInt(6, iterationID);
            stm.setInt(7, subjectID);
            stm.setString(8, description);
            stm.setString(9, criteria_name);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;

        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());

        }
        return false;
    }

    public Criteria getCriteriaByID(int criteriaID) {
        try {
            String sql = "select * from evaluation_criteria where criteria_id=?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, criteriaID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Criteria criteria = new Criteria(rs.getInt("criteria_id"), rs.getInt("evaluation_weight"), rs.getString("team_evaluation"), rs.getInt("criteria_order"), rs.getInt("max_loc"), rs.getInt("status"), rs.getInt("iteration_id"), rs.getInt("subject_id"), rs.getString("description"), rs.getString("criteria_name"));
                stm.close();
                rs.close();
                return criteria;
            }
            stm.close();
            rs.close();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public boolean updateCriteria(int criteriaID, int evaluationWeight, String teamEvaluation, int criteriaOrder, int max_lox, int status, int iterationID , int subjectID , String description , String criteriaName) {
        try {
            String sql = "UPDATE evaluation_criteria SET  evaluation_weight = ?, team_evaluation = ? ,criteria_order = ?, max_loc= ? , status= ? , iteration_id= ? , subject_id=?, description=?, criteria_name=?  WHERE criteria_id = ? ";

            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, evaluationWeight);
            stm.setString(2, teamEvaluation);
            stm.setInt(3, criteriaOrder);
            stm.setInt(4, max_lox);
            stm.setInt(5, status);
            stm.setInt(6, iterationID);
            stm.setInt(7, subjectID);
            stm.setString(8, description);
            stm.setString(9, criteriaName);
            stm.setInt(10, criteriaID);
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

    public String countCriteria() {
        String query = "select COUNT(criteria_id) as NumberOfCriteria from evaluation_criteria";
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String number = rs.getString("NumberOfCriteria");
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

    public boolean changeStatusCriteria(int id) throws Exception {
        boolean check = false;
        Criteria criteria = new Criteria();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql1 = "select * from evaluation_criteria where criteria_id = ?";
            stm = conn.prepareStatement(sql1);
            stm.setObject(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                criteria = new Criteria(rs.getInt("criteria_id"), rs.getInt("evaluation_weight"), rs.getString("team_evaluation"), rs.getInt("criteria_order"), rs.getInt("max_loc"), rs.getInt("status"), rs.getInt("iteration_id"));
            }
            CloseConnection();
        } finally {
            CloseConnection();
        }

        try {
            conn = new SQLServerConnection().getConnection();
            String sqltrue = "UPDATE evaluation_criteria set status=1 where criteria_id = ?";
            String sqlfalse = "UPDATE evaluation_criteria set status=0 where criteria_id = ?";
            if (criteria.getStatus() == 0) {
                stm = conn.prepareStatement(sqltrue);
                stm.setObject(1, id);
                int a = stm.executeUpdate();
                check = a > 0 ? true : false;
                CloseConnection();
            } else if (criteria.getStatus() == 1) {
                stm = conn.prepareStatement(sqlfalse);
                stm.setObject(1, id);
                int a = stm.executeUpdate();
                check = a > 0 ? true : false;
                CloseConnection();
            }
        } catch (Exception ex) {
        }
        return check;
    }

    public ArrayList<Criteria> searchCriteriaByIteration(String iteration) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Criteria> listCriteria = new ArrayList<>();
        String sql = "select criteria_id, evaluation_weight, team_evaluation,criteria_order,max_loc,evaluation_criteria.status,iteration.iteration_name,iteration.status,subject.subject_name,description,criteria_name\n"
                + "                from evaluation_criteria\n"
                + "                inner join iteration on evaluation_criteria.iteration_id = iteration.iteration_id\n"
                + "                inner join subject on evaluation_criteria.subject_id = subject.subject_id"
                + "where iteration.iteration_name like '%" + iteration + "%'";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                listCriteria.add(new Criteria(rs.getInt("criteria_id"), rs.getInt("evaluation_weight"), rs.getString("team_evaluation"), rs.getInt("criteria_order"), rs.getInt("max_loc"), rs.getInt("status"), rs.getString("iteration_name"), rs.getString("subject_name"), rs.getString("description"), rs.getString("criteria_name")));
            }
        } catch (SQLException e) {
        }
        return listCriteria;
    }

    public ArrayList<Criteria> searchCriteriaByTeam(String team) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Criteria> listCriteria = new ArrayList<>();
        String sql = "select criteria_id, evaluation_weight, team_evaluation,criteria_order,max_loc,evaluation_criteria.status,iteration.iteration_name,iteration.status,subject.subject_name,description,criteria_name\n"
                + "                from evaluation_criteria\n"
                + "                inner join iteration on evaluation_criteria.iteration_id = iteration.iteration_id\n"
                + "                inner join subject on evaluation_criteria.subject_id = subject.subject_id"
                + "where team_evaluation like '%" + team + "%'";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                listCriteria.add(new Criteria(rs.getInt("criteria_id"), rs.getInt("evaluation_weight"), rs.getString("team_evaluation"), rs.getInt("criteria_order"), rs.getInt("max_loc"), rs.getInt("status"), rs.getString("iteration_name"), rs.getString("subject_name"), rs.getString("description"), rs.getString("criteria_name")));
            }
        } catch (SQLException e) {
        }
        return listCriteria;
    }

    public static void main(String[] args) throws SQLException, Exception {
       
        // System.out.println(cdao.getCriteriaName("Math 1").toString());
    }

    public List<Criteria> searchCriteria(String subjectID, String iterationID) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<Criteria> listCriteria = new ArrayList<>();
        String sql = "select criteria_id, evaluation_weight, team_evaluation,criteria_order,max_loc,evaluation_criteria.status,iteration.iteration_name,iteration.status,subject.subject_code,description,criteria_name\n"
                + "                from evaluation_criteria\n"
                + "                inner join iteration on evaluation_criteria.iteration_id = iteration.iteration_id\n"
                + "                inner join subject on evaluation_criteria.subject_id = subject.subject_id";

        if (!subjectID.equals("0")) {
            sql += " and subject.subject_id = '" + subjectID + "'";
        }

        if (!iterationID.equals("0")) {
            sql += " and iteration.iteration_id = '" + iterationID + "' ";
        }

       
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                listCriteria.add(new Criteria(rs.getInt("criteria_id"), rs.getInt("evaluation_weight"), rs.getString("team_evaluation"), rs.getInt("criteria_order"), rs.getInt("max_loc"), rs.getInt("status"), rs.getString("iteration_name"), rs.getString("subject_code"), rs.getString("description"), rs.getString("criteria_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCriteria;
    }

    public boolean checkDupCriteria(String criteriaName) {
        boolean check = false;
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "SELECT * from evaluation_criteria where criteria_name =?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, criteriaName);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            CloseConnection();
        } catch (Exception ex) {

        }
        return check;
    }

    public Criteria getCriteriaName(String subjectName) {
        try {
            String sql = "select subject_name , iteration.iteration_name\n"
                    + "from subject\n"
                    + "inner join iteration on subject.subject_id= iteration.subject_id\n"
                    + "where subject_name =?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, subjectName);
            rs = stm.executeQuery();
            while (rs.next()) {
                Criteria criteria = new Criteria(rs.getString("iteration_name"));
                stm.close();
                rs.close();
                return criteria;
            }
            stm.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
