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
import model.Function;
import model.Milestone;
//import model.Milestone;
import model.Team;
import model.Tracking;
import model.User;

/**
 *
 * @author DELL
 */
public class TrackingDAO {

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

    public ArrayList<Tracking> getAllTracking(int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Tracking> listTracking = new ArrayList<>();
        String sql = "select tracking.tracking_id, (select user.full_name\n"
                + "from tracking\n"
                + "inner join user on tracking.assigner_id = user.user_id)assigner,(select user.full_name\n"
                + "from tracking\n"
                + "inner join user on tracking.assignee_id = user.user_id)assignee, tracking.status , \n"
                + "tracking.tracking_note, tracking.updates , team.team_name, functions.function_name ,\n"
                + "milestone.milestone_name , tracking.description\n"
                + "from tracking\n"
                + "inner join user on tracking.assigner_id = user.user_id\n"
                + "inner join team on tracking.team_id  = team.team_id\n"
                + "inner join functions on tracking.function_id = functions.function_id\n"
                + "inner join milestone on tracking.milestone_id = milestone.milestone_id\n"
                + "ORDER BY tracking_id limit 4 OFFSET " + (index - 1) * 4 + "";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                listTracking.add(new Tracking(rs.getInt("tracking_id"), rs.getString("assigner"), rs.getString("assignee"), rs.getInt("status"), rs.getString("tracking_note"), rs.getString("updates"), rs.getString("team_name"), rs.getString("function_name"), rs.getString("milestone_name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTracking;
    }

    public String countTracking() {
        String query = "select COUNT(tracking_id) as NumberOfTracking from tracking";
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String number = rs.getString("NumberOfTracking");
                return number;
            }
            rs.close();
            rs.close();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public List<User> listAssigner() throws SQLException, Exception {
        List<User> listAssigner = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select user_id,full_name\n"
                    + "from user\n"
                    + "where user.status='1' and role_id='2'";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("full_name");
                User user = new User();
                user.setUserId(id);
                user.setFull_name(name);
                listAssigner.add(user);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listAssigner;
    }
    
    public List<User> listAssignee() throws SQLException, Exception {
        List<User> listAssignee = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select user_id,full_name\n"
                    + "from user\n"
                    + "where user.status='1' and role_id='2'";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("full_name");
                User user = new User();
                user.setUserId(id);
                user.setFull_name(name);
                listAssignee.add(user);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listAssignee;
    }
    public List<Team> listTeam() throws SQLException, Exception {
        List<Team> listTeam = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select team_id,team_name\n"
                    + "from team\n"
                    + "where team.status='1'";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("team_id");
                String name = rs.getString("team_name");
                Team team = new Team();
                team.setTeamID(id);
                team.setTeamName(name);
                listTeam.add(team);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listTeam;
    }
    public List<Function> listFunction() throws SQLException, Exception {
        List<Function> listFunction = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select function_id,function_name\n"
                    + "from functions\n"
                    + "where functions.status='1'";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("function_id");
                String name = rs.getString("function_name");
                Function function = new Function();
                function.setFunctionID(id);
                function.setFunctionName(name);
                listFunction.add(function);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listFunction;
    }
    public List<Milestone> listMilestone() throws SQLException, Exception {
        List<Milestone> listMilestone = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select milestone_id,milestone_name\n"
                    + "from milestone\n"
                    + "where milestone.status='1'";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("milestone_id");
                String name = rs.getString("milestone_name");
                Milestone milestone = new Milestone();
                milestone.setMilestoneId(id);
                milestone.setMilestoneName(name);
                listMilestone.add(milestone);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listMilestone;
    }
    public boolean createTracking(int assignerID, int assigneeID, int status, String trackingNote, String update, int teamID, int functionID, int milestoneID,String description) {
        try {
            String sql = "INSERT INTO tracking(assigner_id,assignee_id,status,tracking_note,updates,team_id,function_id,milestone_id,description) VALUES (?,?,?,?,?,?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, assignerID);
            stm.setInt(2, assigneeID);
            stm.setInt(3, status);
            stm.setString(4, trackingNote);
            stm.setString(5, update);
            stm.setInt(6, teamID);
            stm.setInt(7, functionID);
            stm.setInt(8, milestoneID);
            stm.setString(9, description);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;
        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());
        }
        return false;
    }
}
