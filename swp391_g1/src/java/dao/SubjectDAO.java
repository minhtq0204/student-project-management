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

import model.Subject;
import model.User;

/**
 *
 * @author DELL
 */
public class SubjectDAO {

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

    public boolean checkDupSubjectCode(String subjectCode) {
        boolean check = false;
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "SELECT * from subject where subject_code =?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, subjectCode);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            CloseConnection();
        } catch (Exception ex) {

        }
        return check;
    }

    public boolean checkDupSubjectName(String subjectName) {
        boolean check = false;
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "SELECT * from subject where subject_name =?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, subjectName);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            CloseConnection();
        } catch (Exception ex) {

        }
        return check;
    }

    public boolean createSubject(String subjectCode, String subjectName, int authorID, int status, String description) {
        try {
            String sql = "INSERT INTO subject (subject_code,subject_name,author_id,status,subject_description) VALUES (?,?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, subjectCode);
            stm.setString(2, subjectName);
            stm.setInt(3, authorID);
            stm.setInt(4, status);
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

    // sửa lọc theo những subject có status = active
    public List<Subject> list() throws SQLException, Exception {
        List<Subject> listSubject = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select * from subject order by subject_code";
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

    public List<User> listAuthor() throws SQLException, Exception {
        List<User> listUser = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select * from user where status = 1 and role_id = 3";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("full_name");
                User user = new User(id, name, 3, 1);
                listUser.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listUser;
    }

    public ArrayList<Subject> getAllSubject(int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Subject> list = new ArrayList<>();
        String sql = "select subject.subject_id, subject.subject_code , subject.subject_name , user.full_name , subject.status , subject.subject_description\n"
                + "from subject\n"
                + "inner join user on subject.author_id = user.user_id\n"
                + "where user.role_id='3'\n"
                + "ORDER BY subject_id limit 5 OFFSET " + (index - 1) * 5 + "";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Subject(rs.getInt("subject_id"), rs.getString("subject_code"),
                        rs.getString("subject_name"), rs.getInt("status"),
                        rs.getString("full_name"), rs.getString("subject_description")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String countSubject() {
        String query = "select COUNT(subject_id) as NumberOfSubject from subject";
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String number = rs.getString("NumberOfSubject");
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

    public boolean changeStatusSubject(int id) throws Exception {
        boolean check = false;
        Subject accCheck = new Subject();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql1 = "select * from subject where subject_id = ?";
            stm = conn.prepareStatement(sql1);
            stm.setObject(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                accCheck = new Subject(rs.getInt("subject_id"), rs.getString("subject_code"), rs.getString("subject_name"), rs.getInt("author_id"), rs.getInt("status"), rs.getString("subject_description"));

            }
            CloseConnection();
        } finally {
            CloseConnection();
        }

        try {
            conn = new SQLServerConnection().getConnection();
            String sqltrue = "UPDATE subject set status=1 where subject_id = ?";
            String sqlfalse = "UPDATE subject set status=0 where subject_id = ?";
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

    public boolean updateSubject(int subjectID, String subjectCode, String subjectName,
            int authorID, int status, String description) {
        try {
            String sql = "UPDATE subject SET subject_code = ?, subject_name = ?, author_id = ? ,status = ?,subject_description = ? WHERE subject_id = ? ";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, subjectCode);
            stm.setString(2, subjectName);
            stm.setInt(3, authorID);
            stm.setInt(4, status);
            stm.setString(5, description);
            stm.setInt(6, subjectID);
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

    public Subject getSubjectByID(int id) {
        try {
            String sql = "select * from subject where subject_id=?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject(rs.getInt("subject_id"), rs.getString("subject_code"),
                        rs.getString("subject_name"), rs.getInt("author_id"), rs.getInt("status"), rs.getString("subject_description"));
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

    public ArrayList<Subject> searchSubjectByName(String name) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Subject> list = new ArrayList<>();
        String sql = "select a.subject_id, a.subject_code, a.subject_name, a.author_id, a.status, "
                + "b.full_name, a.subject_description from subject as a, user as b "
                + "where a.author_id = b.user_id and a.subject_name like '%" + name + "%'";

        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {

                list.add(new Subject(rs.getInt("subject_id"), rs.getString("subject_code"), rs.getString("subject_name"), rs.getInt("author_id"), rs.getInt("status"), rs.getString("full_name"), rs.getString("subject_description")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Subject> searchSubjectByCode(String code) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Subject> list = new ArrayList<>();

        String sql = "select a.subject_id, a.subject_code, a.subject_name, a.author_id, a.status, b.full_name, a.subject_description from subject as a, user as b where a.author_id = b.user_id and a.subject_code like '%" + code + "%'";

        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {

                list.add(new Subject(rs.getInt("subject_id"), rs.getString("subject_code"), rs.getString("subject_name"), rs.getInt("author_id"), rs.getInt("status"), rs.getString("subject_description")));

                list.add(new Subject(rs.getInt("subject_id"), rs.getString("subject_code"), rs.getString("subject_name"), rs.getInt("author_id"), rs.getInt("status"), rs.getString("full_name"), rs.getString("subject_description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Subject> searchSubjectByAuthor(String name) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Subject> list = new ArrayList<>();
        String sql = "select a.subject_id, a.subject_code, a.subject_name, a.author_id, a.status, b.full_name, a.subject_description from subject as a, user as b where a.author_id = b.user_id and b.full_name like '%" + name + "%'";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Subject(rs.getInt("subject_id"), rs.getString("subject_code"), rs.getString("subject_name"), rs.getInt("author_id"), rs.getInt("status"), rs.getString("full_name"), rs.getString("subject_description")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Subject> searchSubject(String status,
            String authorID, String txtSearch) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<Subject> list = new ArrayList<>();
        String sql = "select subject.subject_id, subject.subject_code , subject.subject_name,"
                + " user.full_name, subject.status, subject.subject_description, subject.author_id"
                + " from subject"
                + " inner join user on subject.author_id = user.user_id"
                + " where user.role_id='3'\n";

        if (!status.equals("2")) {
            sql += " and subject.status = '" + status + "'";
        }

        if (!authorID.equals("0")) {
            sql += " and user.user_id = '" + authorID + "' ";
        }

        if (txtSearch != null) {
            sql += " and subject.subject_name like '%" + txtSearch + "%'";
        }
        System.out.println(sql);
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Subject(rs.getInt("subject_id"), rs.getString("subject_code"), rs.getString("subject_name"), rs.getInt("author_id"), rs.getInt("status"), rs.getString("full_name"), rs.getString("subject_description")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws SQLException, Exception {
        SubjectDAO x = new SubjectDAO();
        
    }

}
