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
import model.Function;
import model.Issue;
import model.Team;
import model.User;

/**
 *
 * @author DELL
 */
public class IssueDAO {

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

    public ArrayList<Issue> getAllIssue(int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Issue> listIssue = new ArrayList<>();
        String sql = "select issue_id,issue_title , issue.description, createdAt, issue.gitlab_url, functions.function_name, issue.status , user.full_name , team.team_name\n"
                + "from issue\n"
                + "inner join functions on issue.function_ids = functions.function_id\n"
                + "inner join user on issue.assignee_id = user.user_id\n"
                + "inner join team on issue.team_id = team.team_id\n"
                + "ORDER BY issue_id limit 5 OFFSET " + (index - 1) * 5 + "";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                listIssue.add(new Issue(rs.getInt("issue_id"), rs.getString("issue_title"), rs.getString("description"), rs.getString("createdAt"), rs.getString("gitlab_url"), rs.getString("function_name"), rs.getInt("status"), rs.getString("full_name"), rs.getString("team_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listIssue;
    }

    public String countIssue() {
        String query = "select COUNT(issue_id) as NumberOfIssue from issue";
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String number = rs.getString("NumberOfIssue");
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

    public List<User> listUser() throws SQLException, Exception {
        List<User> listUser = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select user_id,full_name\n"
                    + "from user\n"
                    + "where user.status='1'";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("full_name");
                User user = new User();
                user.setUserId(id);
                user.setFull_name(name);
                listUser.add(user);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listUser;
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

    public boolean createIssue(String issueTitle, String description, String dueDate, String gitlab, int functionID, int status, int assignID, int teamID) {
        try {
            String sql = "INSERT INTO issue (issue_title,description,due_date,gitlab_url,function_ids,status,assignee_id,team_id) VALUES (?,?,?,?,?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, issueTitle);
            stm.setString(2, description);
            stm.setString(3, dueDate);
            stm.setString(4, gitlab);
            stm.setInt(5, functionID);
            stm.setInt(6, status);
            stm.setInt(7, assignID);
            stm.setInt(8, teamID);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;
        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());
        }
        return false;
    }

    public Issue getIssueID(int issueID) {
        try {
            String sql = "select * from issue where issue_id=?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, issueID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Issue issue = new Issue(rs.getInt("issue_id"), rs.getString("issue_title"), rs.getString("description"), rs.getString("due_date"), rs.getString("createdAt"), rs.getString("gitlab_url"), rs.getInt("function_ids"), rs.getInt("status"), rs.getInt("assignee_id"), rs.getInt("team_id"));
                stm.close();
                rs.close();
                return issue;
            }
            stm.close();
            rs.close();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public boolean updateIssue(int issueID, String issueTitle, String description, String dueDate, String gitlab, int functionID, int status, int assignID, int teamID) {
        try {
            String sql = "UPDATE issue SET  issue_title = ?, description = ? ,due_date = ?, gitlab_url= ? , function_ids= ? , status= ? , assignee_id=?, team_id=?  WHERE issue_id = ? ";

            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, issueTitle);
            stm.setString(2, description);
            stm.setString(3, dueDate);
            stm.setString(4, gitlab);
            stm.setInt(5, functionID);
            stm.setInt(6, status);
            stm.setInt(7, assignID);
            stm.setInt(8, teamID);
            stm.setInt(9, issueID);
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

    public boolean changeStatusIssue(int id) throws Exception {
        boolean check = false;
        Issue issue = new Issue();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql1 = "select * from issue where issue_id = ?";
            stm = conn.prepareStatement(sql1);
            stm.setObject(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                issue = new Issue(rs.getInt("issue_id"), rs.getString("issue_title"), rs.getString("description"), rs.getString("due_date"), rs.getString("createdAt"), rs.getString("gitlab_url"), rs.getInt("function_ids"), rs.getInt("status"), rs.getInt("assignee_id"), rs.getInt("team_id"));
            }
            CloseConnection();
        } finally {
            CloseConnection();
        }

        try {
            conn = new SQLServerConnection().getConnection();
            String sqltrue = "UPDATE issue set status=1 where issue_id = ?";
            String sqlfalse = "UPDATE issue set status=0 where issue_id = ?";
            if (issue.getStatus() == 0) {
                stm = conn.prepareStatement(sqltrue);
                stm.setObject(1, id);
                int a = stm.executeUpdate();
                check = a > 0 ? true : false;
                CloseConnection();
            } else if (issue.getStatus() == 1) {
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

    public List<Issue> searchIssue(String issueTitle) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<Issue> listIssue = new ArrayList<>();
        String sql = "select issue_id,issue_title , issue.description, createdAt, issue.gitlab_url, functions.function_name, issue.status , user.full_name , team.team_name\n"
                + "from issue\n"
                + "inner join functions on issue.function_ids = functions.function_id\n"
                + "inner join user on issue.assignee_id = user.user_id\n"
                + "inner join team on issue.team_id = team.team_id\n";
        if (!issueTitle.equals("0")) {
            sql += " and issue.issue_title = '" + issueTitle + "'";
        }
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                listIssue.add(new Issue(rs.getInt("issue_id"), rs.getString("issue_title"), rs.getString("description"), rs.getString("createdAt"), rs.getString("gitlab_url"), rs.getString("function_name"), rs.getInt("status"), rs.getString("full_name"), rs.getString("team_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listIssue;
    }

    public List<Issue> listIssueExport() throws SQLException, Exception {
        List<Issue> listIssue = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select issue_id,issue_title , issue.description, createdAt, issue.gitlab_url, functions.function_name, issue.status , user.full_name , team.team_name\n"
                    + "from issue\n"
                    + "inner join functions on issue.function_ids = functions.function_id\n"
                    + "inner join user on issue.assignee_id = user.user_id\n"
                    + "inner join team on issue.team_id = team.team_id\n";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                listIssue.add(new Issue(rs.getInt("issue_id"), rs.getString("issue_title"), rs.getString("description"), rs.getString("createdAt"), rs.getString("gitlab_url"), rs.getString("function_name"), rs.getInt("status"), rs.getString("full_name"), rs.getString("team_name")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return listIssue;
    }

    public List<Issue> listIssueImport() throws SQLException, Exception {
        List<Issue> listIssue = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select issue_title, issue.description, issue.gitlab_url , function_ids , functions.function_name, issue.status, assignee_id, user.full_name, issue.team_id, team.team_name\n"
                    + "from issue\n"
                    + "inner join functions on issue.function_ids = functions.function_id\n"
                    + "inner join user on issue.assignee_id = user.user_id\n"
                    + "inner join team on issue.team_id = team.team_id;";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                listIssue.add(new Issue(rs.getString("issue_title"), rs.getString("description"), rs.getString("gitlab_url"), rs.getInt("function_ids"),rs.getString("function_name"), rs.getInt("status"),rs.getInt("assignee_id") ,rs.getString("full_name"), rs.getInt("team_id"),rs.getString("team_name")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return listIssue;
    }

    public boolean createIssue1(String issueTitle, String description, String gitlab, int functionID, int status, int assignID, int teamID) {
        try {
            String sql = "INSERT INTO issue (issue_title,description,gitlab_url,function_ids,status,assignee_id,team_id) VALUES (?,?,?,?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, issueTitle);
            stm.setString(2, description);
            stm.setString(3, gitlab);
            stm.setInt(4, functionID);
            stm.setInt(5, status);
            stm.setInt(6, assignID);
            stm.setInt(7, teamID);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;
        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());
        }
        return false;
    }
    public List<Issue> listIssueTitle() throws SQLException, Exception {
        List<Issue> listIssues = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select issue_title\n"
                    + "from issue\n";
                    
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
               
                String name = rs.getString("issue_title");
                Issue issue = new Issue();
                issue.setIssueTitle(name);
                listIssues.add(issue);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listIssues;
    }
  

}
