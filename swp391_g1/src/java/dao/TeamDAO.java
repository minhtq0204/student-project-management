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
import model.Class;
import model.Team;

/**
 *
 * @author mac
 */
public class TeamDAO {

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

    public boolean checkDupTopicCode(String topicCode) {
        boolean check = false;
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "SELECT * from team where topic_code = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, topicCode);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            CloseConnection();
        } catch (Exception ex) {

        }
        return check;
    }

    public boolean checkDupTopicName(String topicName) {
        boolean check = false;
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "SELECT * from team where topic_name = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, topicName);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            CloseConnection();
        } catch (Exception ex) {

        }
        return check;
    }

    public boolean createTeam(String teamName, String topicCode, String topicName,
            String gitURL, String description, int status, int classID) {
        try {
            String sql = "INSERT INTO team (team_name,topic_code,topic_name,gitlab_url,description,status,class_id)"
                    + " VALUES (?,?,?,?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, teamName);
            stm.setString(2, topicCode);
            stm.setString(3, topicName);
            stm.setString(4, gitURL);
            stm.setString(5, description);
            stm.setInt(6, status);
            stm.setInt(7, classID);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;

        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());

        }
        return false;
    }

    public List<Class> listClass() throws SQLException, Exception {
        List<Class> listClass = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select * from class where status = 1";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("class_id");
                String name = rs.getString("class_code");
                Class class1 = new Class(id, name, 1);
                listClass.add(class1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listClass;
    }

    public List<Team> listTopicName() throws SQLException, Exception {
        List<Team> listTopicName = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select topic_name from team";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("topic_name");
                Team team = new Team();
                team.setTopicName(name);
                listTopicName.add(team);                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return listTopicName;
    }

    public List<Team> list() throws SQLException, Exception {
        List<Team> listTeam = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select * from team order by topic_code";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("team_id");
                String name = rs.getString("team_name");
                String topicCode = rs.getString("topic_code");
                String topicName = rs.getString("topic_name");
                Team team = new Team(id, name, topicCode, topicName);
                listTeam.add(team);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listTeam;
    }

    public ArrayList<Team> getAllTeam(int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Team> list = new ArrayList<>();
        String sql = "select a.team_id, a.team_name, a.topic_code, a.topic_name, a.class_id,"
                + " a.status, b.class_code, a.gitlab_url, a.description "
                + "from team as a, class as b where a.class_id = b.class_id \n"
                + "ORDER BY team_id limit 5 OFFSET " + (index - 1) * 5 + "";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Team(rs.getInt("team_id"), rs.getString("team_name"), rs.getString("topic_code"), rs.getString("topic_name"),
                        rs.getString("gitlab_url"), rs.getString("description"),
                        rs.getInt("status"), rs.getInt("class_id"), rs.getString("class_code")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String countTeam() {
        String query = "select COUNT(team_id) as NumberOfTeam from team";
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String number = rs.getString("NumberOfTeam");
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

    public boolean changeStatusTeam(int id) throws Exception {
        boolean check = false;
        Team accCheck = new Team();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql1 = "select * from team where team_id = ?";
            stm = conn.prepareStatement(sql1);
            stm.setObject(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                accCheck = new Team(rs.getInt("team_id"), rs.getString("team_name"), rs.getString("topic_code"),
                        rs.getString("topic_name"), rs.getString("gitlab_url"), rs.getString("description"),
                        rs.getInt("status"), rs.getInt("class_id"));

            }
            CloseConnection();
        } finally {
            CloseConnection();
        }

        try {
            conn = new SQLServerConnection().getConnection();
            String sqltrue = "UPDATE team set status=1 where team_id = ?";
            String sqlfalse = "UPDATE team set status=0 where team_id = ?";
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

    public boolean updateTeam(int teamID, String teamName, String topicCode, String topicName,
            String gitURL, String description, int status, int classID) {

        try {
            String sql = "UPDATE team SET team_name = ?, topic_code = ?, topic_name = ?,"
                    + " gitlab_url = ?, description = ?, class_id = ? ,status = ? WHERE team_id = ? ";

            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, teamName);
            stm.setString(2, topicCode);
            stm.setString(3, topicName);
            stm.setString(4, gitURL);
            stm.setString(5, description);
            stm.setInt(6, classID);
            stm.setInt(7, status);
            stm.setInt(8, teamID);
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

    public Team getTeamByID(int id) {
        try {
            String sql = "select * from team where team_id=?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Team team = new Team(rs.getInt("team_id"), rs.getString("team_name"), rs.getString("topic_code"),
                        rs.getString("topic_name"), rs.getString("gitlab_url"), rs.getString("description"),
                        rs.getInt("status"), rs.getInt("class_id"));
                stm.close();
                rs.close();
                return team;
            }
            stm.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public List<Team> searchTeam(String status, String classID, String txtSearch, String teamName) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<Team> list = new ArrayList<>();
        String sql = "select a.team_id, a.team_name, a.topic_code, a.topic_name,"
                + " a.class_id, a.status, b.class_code, a.gitlab_url, a.description"
                + " from team as a, class as b where"
                + " a.class_id = b.class_id";

        if (!status.equals("2")) {
            sql += " and a.status = '" + status + "'";
        }

        if (!classID.equals("0")) {
            sql += " and a.class_id = '" + classID + "' ";
        }

        if (txtSearch != null) {
            sql += " and a.team_name like '%" + txtSearch + "%'";
        }
        System.out.println(sql);
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Team(rs.getInt("team_id"), rs.getString("team_name"), rs.getString("topic_code"), rs.getString("topic_name"),
                        rs.getString("gitlab_url"), rs.getString("description"),
                        rs.getInt("status"), rs.getInt("class_id"), rs.getString("class_code")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws SQLException, Exception {
        TeamDAO x = new TeamDAO();
        
    }
}
