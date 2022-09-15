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
import model.Feature;
import model.Function;
import model.Team;
import model.User;

/**
 *
 * @author mac
 */
public class FunctionDAO {

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

    public boolean checkDupFunctionName(String functionName) {
        boolean check = false;
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "SELECT * from functions where function_name = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, functionName);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            CloseConnection();
        } catch (Exception ex) {

        }
        return check;
    }

    public boolean createFunction(String functionName, String accessRoles, String description,
            int complexityID, int priority, int status, int teamID, int featureID, int ownerID) {
        try {
            String sql = "INSERT INTO functions (function_name,access_roles,description,"
                    + "complexity_id,priority,status,team_id,feature_id,owner_id)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, functionName);
            stm.setString(2, accessRoles);
            stm.setString(3, description);
            stm.setInt(4, complexityID);
            stm.setInt(5, priority);
            stm.setInt(6, status);
            stm.setInt(7, teamID);
            stm.setInt(8, featureID);
            stm.setInt(9, ownerID);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;

        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());

        }
        return false;
    }

    public List<Team> listTeam() throws SQLException, Exception {
        List<Team> listTeam = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select * from team where status = 1";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("team_id");
                String name = rs.getString("team_name");
                Team team = new Team(id, name, 1);
                listTeam.add(team);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listTeam;
    }

    public List<Feature> listFeature() throws SQLException, Exception {
        List<Feature> listFeature = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select * from feature where status = 1";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("feature_id");
                String name = rs.getString("feature_name");
                Feature feature = new Feature(id, name, 1);
                listFeature.add(feature);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listFeature;
    }

    public List<User> listOwner() throws SQLException, Exception {
        List<User> listUser = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select * from user where status = 1 and role_id = 2";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("full_name");
                User user = new User(id, name, 2, 1);
                listUser.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listUser;
    }

    public List<Function> listFunctionExport() throws SQLException, Exception {
        List<Function> list = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select a.function_id, a.function_name, a.access_roles, a.description,"
                    + " a.complexity_id, a.priority, a.status,"
                    + " a.team_id, a.feature_id, a.owner_id,"
                    + " b.team_name, c.feature_name, d.full_name"
                    + " from functions as a join team as b join feature as c join user as d"
                    + " where a.team_id = b.team_id and a.feature_id = c.feature_id"
                    + " and a.owner_id = d.user_id \n";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Function(rs.getInt("function_id"), rs.getString("function_name"),
                        rs.getString("access_roles"), rs.getString("description"),
                        rs.getInt("complexity_id"), rs.getInt("priority"),
                        rs.getInt("status"), rs.getInt("team_id"),
                        rs.getString("team_name"), rs.getInt("feature_id"),
                        rs.getString("feature_name"), rs.getInt("owner_id"), rs.getString("full_name")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return list;
    }

    public List<Function> listFunctionImport() throws SQLException, Exception {
        List<Function> list = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select a.function_name, a.access_roles, a.description,"
                    + " a.complexity_id, a.priority, a.status,"
                    + " a.team_id, a.feature_id, a.owner_id,"
                    + " b.team_name, c.feature_name, d.full_name"
                    + " from functions as a join team as b join feature as c join user as d"
                    + " where a.team_id = b.team_id and a.feature_id = c.feature_id"
                    + " and a.owner_id = d.user_id \n";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Function(rs.getString("function_name"),
                        rs.getString("access_roles"), rs.getString("description"),
                        rs.getInt("complexity_id"), rs.getInt("priority"),
                        rs.getInt("status"), rs.getInt("team_id"),
                        rs.getString("team_name"), rs.getInt("feature_id"),
                        rs.getString("feature_name"), rs.getInt("owner_id"), rs.getString("full_name")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return list;
    }

    public ArrayList<Function> getAllFunctions(int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Function> list = new ArrayList<>();
        String sql = "select functions.function_id,functions.function_name, functions.access_roles, functions.description, functions.complexity_id, user.full_name, functions.priority, functions.status, team.team_name, feature.feature_name\n"
                + "from functions\n"
                + "inner join user on functions.owner_id = user.user_id\n"
                + "inner join team on functions.team_id = team.team_id\n"
                + "inner join feature on functions.feature_id = feature.feature_id\n"
                + " ORDER BY function_id limit 5 OFFSET " + (index - 1) * 5 + "";

        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Function(rs.getInt("function_id"), rs.getString("function_name"),
                        rs.getString("access_roles"), rs.getString("description"),
                        rs.getInt("complexity_id"), rs.getInt("priority"),
                        rs.getInt("status"),
                        rs.getString("team_name"),
                        rs.getString("feature_name"), rs.getString("full_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String countFunction() {
        String query = "select COUNT(function_id) as NumberOfFunction from functions";
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String number = rs.getString("NumberOfFunction");
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

    public List<Function> searchFunction(String status, String teamID, String featureID, String txtSearch) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<Function> list = new ArrayList<>();
        String sql = "select a.function_id, a.function_name, a.access_roles, a.description,"
                + " a.complexity_id, a.priority, a.status,"
                + " a.team_id, a.feature_id, a.owner_id,"
                + " b.team_name, c.feature_name, d.full_name"
                + " from functions as a join team as b join feature as c join user as d"
                + " where a.team_id = b.team_id and a.feature_id = c.feature_id"
                + " and a.owner_id = d.user_id";

        if (!status.equals("4")) {
            sql += " and a.status = '" + status + "'";
        }

        if (!featureID.equals("0")) {
            sql += " and a.feature_id = '" + featureID + "' ";
        }

        if (!teamID.equals("0")) {
            sql += " and a.team_id = '" + teamID + "' ";
        }

        if (txtSearch != null) {
            sql += " and a.function_name like '%" + txtSearch + "%'";
        }
        System.out.println(sql);
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Function(rs.getInt("function_id"), rs.getString("function_name"),
                        rs.getString("access_roles"), rs.getString("description"),
                        rs.getInt("complexity_id"), rs.getInt("priority"),
                        rs.getInt("status"), rs.getInt("team_id"),
                        rs.getString("team_name"), rs.getInt("feature_id"),
                        rs.getString("feature_name"), rs.getInt("owner_id"), rs.getString("full_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateFunction(int functionID, String functionName, String accessRoles, String description,
            int complexityID, int priority, int status, int teamID, int featureID, int ownerID) {

        try {
            String sql = "UPDATE functions SET function_name = ?, access_roles = ?, description = ?,"
                    + " complexity_id = ?, priority = ?, status = ? ,team_id = ?, feature_id = ?, owner_id =?"
                    + " WHERE function_id = ? ";

            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, functionName);
            stm.setString(2, accessRoles);
            stm.setString(3, description);
            stm.setInt(4, complexityID);
            stm.setInt(5, priority);
            stm.setInt(6, status);
            stm.setInt(7, teamID);
            stm.setInt(8, featureID);
            stm.setInt(9, ownerID);
            stm.setInt(10, functionID);
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

    public Function getFunctionByID(int id) {
        try {
            String sql = "select * from functions where function_id=?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Function function = new Function(rs.getInt("function_id"), rs.getString("function_name"), rs.getString("access_roles"),
                        rs.getString("description"), rs.getInt("complexity_id"), rs.getInt("priority"),
                        rs.getInt("status"), rs.getInt("team_id"), rs.getInt("feature_id"), rs.getInt("owner_id"));
                stm.close();
                rs.close();
                return function;
            }
            stm.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static void main(String[] args) throws SQLException, Exception {
        FunctionDAO dao = new FunctionDAO();
        List<Team> team = dao.listTeam();
        for (Team t : team) {
            System.out.println(t);
        }

        List<Feature> team1 = dao.listFeature();
        for (Feature t : team1) {
            System.out.println(t);
        }

        List<User> team2 = dao.listOwner();
        for (User t : team2) {
            System.out.println(t);
        }

        List<Function> functions = dao.listFunctionExport();
        for (Function f : functions) {
            System.out.println(f);
        }

    }
}
