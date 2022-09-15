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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feature;
import model.Team;
import model.Class;

/**
 *
 * @author minhc
 */
public class FeatureDAO {

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

    public List<Team> listTeam() throws Exception {
        List<Team> listTeam = new ArrayList<>();
        try {
            String sql = "select team_id,topic_name  from team";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("team_id");
                String name = rs.getString("topic_name");
                model.Team team = new Team();
                team.setTeamID(id);
                team.setTopicName(name);
                listTeam.add(team);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listTeam;
    }

    public List<model.Class> listClass() throws SQLException, Exception {
        List<model.Class> listClass = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select class_id,class_code\n"
                    + "from class\n";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("class_id");
                String name = rs.getString("class_code");
                model.Class c = new Class();
                c.setClassId(id);
                c.setClassCode(name);
                listClass.add(c);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listClass;
    }

    public boolean updateFeature(int fetureID, String featureName, int status, int teamID, int classID) {
        try {
            String sql = "UPDATE feature SET  feature_name = ?, status = ? ,team_id = ?, class_id= ?  WHERE feature_id = ? ";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, featureName);
            stm.setInt(2, status);
            stm.setInt(3, teamID);
            stm.setInt(4, classID);
            stm.setInt(5, fetureID);
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

    public boolean createFeature(String featureName, int status, int teamID, int classID) {
        try {
            String sql = "INSERT INTO feature (feature_name,status,team_id,class_id) VALUES (?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, featureName);
            stm.setInt(2, status);
            stm.setInt(3, teamID);
            stm.setInt(4, classID);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;
        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());
        }
        return false;
    }

    public boolean changeStatusFeature(int id) throws Exception {
        boolean check = false;
        Feature feature = new Feature();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql1 = "select * from feature where feature_id = ?";
            stm = conn.prepareStatement(sql1);
            stm.setObject(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                feature = new Feature(rs.getInt("feature_id"), rs.getString("feature_name"), rs.getInt("status"), rs.getInt("team_id"), rs.getInt("class_id"));
            }
            CloseConnection();
        } finally {
            CloseConnection();
        }

        try {
            conn = new SQLServerConnection().getConnection();
            String sqltrue = "UPDATE feature set status=1 where feature_id = ?";
            String sqlfalse = "UPDATE feature set status=0 where feature_id = ?";
            if (feature.getStatus() == 0) {
                stm = conn.prepareStatement(sqltrue);
                stm.setObject(1, id);
                int a = stm.executeUpdate();
                check = a > 0 ? true : false;
                CloseConnection();
            } else if (feature.getStatus() == 1) {
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

    public Feature getFeatureID(int featureID) {
        try {
            String sql = "select * from feature where feature_id=?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, featureID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Feature feature = new Feature(rs.getInt("feature_id"), rs.getString("feature_name"), rs.getInt("status"), rs.getInt("team_id"), rs.getInt("class_id"));
                stm.close();
                rs.close();
                return feature;
            }
            stm.close();
            rs.close();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public ArrayList<Feature> getAllFeature(int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Feature> listFeature = new ArrayList<>();
        String sql = "select feature.feature_id, feature.feature_name , feature.status, team.topic_name , class.class_code\n"
                + "from feature\n"
                + "inner join team on feature.team_id = team.team_id\n"
                + "inner join class on feature.class_id = class.class_id\n"
                 + "ORDER BY feature_id limit 5 OFFSET " + (index - 1) * 5 + "";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                listFeature.add(new Feature(rs.getInt("feature_id"), rs.getString("feature_name"), rs.getInt("status"), rs.getString("topic_name"), rs.getString("class_code")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFeature;
    }

    public String countFeature() {
        String sql = "select COUNT(feature_id) as NumberOfFeature from feature";
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

    public List<Feature> searchFeature(String status, String teamID, String classID, String txtSearch) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<Feature> list = new ArrayList<>();
        String sql = "select feature.feature_id, feature.feature_name , feature.status, team.topic_name , class.class_code\n"
                + "from feature\n"
                + "inner join team on feature.team_id = team.team_id\n"
                + "inner join class on feature.class_id = class.class_id\n";

        if (!status.equals("2")) {
            sql += " and feature.status = '" + status + "'";
        }

        if (!classID.equals("0")) {
            sql += " and class.class_id = '" + classID + "' ";
        }

        if (!teamID.equals("0")) {
            sql += " and team.team_id  = '" + teamID + "' ";
        }

        if (txtSearch != null) {
            sql += " and feature.feature_name  like '%" + txtSearch + "%'";
        }
        System.out.println(sql);
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Feature(rs.getInt("feature_id"), rs.getString("feature_name"), rs.getInt("status"), rs.getString("topic_name"), rs.getString("class_code")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws SQLException, Exception {
        FeatureDAO dao = new FeatureDAO();
        dao.getAllFeature(1);
    }

}
