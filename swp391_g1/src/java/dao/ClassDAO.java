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
import model.Subject;
import model.User;

/**
 *
 * @author Thanh
 */
public class ClassDAO {

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

    public ArrayList<Class> getAllClass(int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Class> list = new ArrayList<>();
        String sql = "select class.class_id , class.class_code , class.class_year ,"
                + " class.class_term ,class.block5_class , class.status , user.full_name , subject.subject_code\n"
                + "from class\n"
                + "inner join user on class.trainer_id = user.user_id\n"
                + "inner join subject on class.subject_id = subject.subject_id where user.role_id = 2"
                + " ORDER BY class.class_id limit 5 OFFSET " + (index - 1) * 5 + "";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Class(rs.getInt("class_id"), rs.getString("class_code"),
                        rs.getInt("class_year"), rs.getString("class_term"), rs.getInt("block5_class"),
                        rs.getInt("status"), rs.getString("full_name"), rs.getString("subject_code")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean changeStatusClass(int id) throws Exception {
        boolean check = false;
        Class accCheck = new Class();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql1 = "select * from class where class_id = ?";
            stm = conn.prepareStatement(sql1);
            stm.setObject(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                accCheck = new Class(rs.getInt("class_id"), rs.getString("class_code"), rs.getInt("class_year"),
                        rs.getString("class_term"), rs.getInt("block5_class"), rs.getInt("status"),
                        rs.getInt("trainer_id"), rs.getInt("subject_id"));

            }
            CloseConnection();
        } finally {
            CloseConnection();
        }

        try {
            conn = new SQLServerConnection().getConnection();
            String sqltrue = "UPDATE class set status=1 where class_id = ?";
            String sqlfalse = "UPDATE class set status=0 where class_id = ?";
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

    public boolean changeStatusBlock5Class(int id) throws Exception {
        boolean check = false;
        Class accCheck = new Class();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql1 = "select * from class where class_id = ?";
            stm = conn.prepareStatement(sql1);
            stm.setObject(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                accCheck = new Class(rs.getInt("class_id"), rs.getString("class_code"), rs.getInt("class_year"), rs.getString("class_term"), rs.getInt("block5_class"), rs.getInt("status"), rs.getString("full_name"), rs.getString("subject_name"));

            }
            CloseConnection();
        } finally {
            CloseConnection();
        }

        try {
            conn = new SQLServerConnection().getConnection();
            String sqltrue = "UPDATE class set block5_class=1 where class_id = ?";
            String sqlfalse = "UPDATE class set block5_class=0 where class_id = ?";
            if (accCheck.getBlock5Class() == 0) {
                stm = conn.prepareStatement(sqltrue);
                stm.setObject(1, id);
                int a = stm.executeUpdate();
                check = a > 0 ? true : false;
                CloseConnection();
            } else if (accCheck.getBlock5Class() == 1) {
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

    public ArrayList<Class> searchClass(String classCode) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<Class> list = new ArrayList<>();
        String sql = "select * from class where class_code like '%" + classCode + "%'";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Class(rs.getInt("class_id"), rs.getString("class_code"), rs.getInt("class_year"), rs.getString("class_term"), rs.getInt("block5_class"), rs.getInt("status"), rs.getString("full_name"), rs.getString("subject_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Class getOneClass(int id) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        Class list = null;
        String sql = "select * from class where class_id=?";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            while (rs.next()) {
                list = new Class(rs.getInt("class_id"), rs.getString("class_code"), rs.getInt("class_year"),
                        rs.getString("class_term"), rs.getInt("block5_class"), rs.getInt("status"),
                        rs.getInt("subject_id"), rs.getInt("trainer_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateClass(int classId, String classCode, int classYear, String classTerm,
            int block5, int status, int trainerId, int subjectId) throws Exception {
        try {
            conn = new SQLServerConnection().getConnection();
            String sqltrue = "UPDATE class set class_code=?, class_year=?, class_term=?, block5_class=?,"
                    + " status=?, trainer_id=?, subject_id = ? where class_id = ?";
            stm = conn.prepareStatement(sqltrue);
            stm.setString(1, classCode);
            stm.setInt(2, classYear);
            stm.setString(3, classTerm);
            stm.setInt(4, block5);
            stm.setInt(5, status);
            stm.setInt(6, trainerId);
            stm.setInt(7, subjectId);
            stm.setInt(8, classId);
            System.out.println(sqltrue);
            System.out.println("Update OK");
            stm.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Update fail" + ex.getMessage());
        }
        return false;
    }

    public boolean createClass(String classCode, int classYear, String classTerm, int block5, int status, int trainerId, int subjectId) {
        try {
            String sql = "INSERT INTO Class (class_code,class_year,class_term,block5_class,status,trainer_id,subject_id)"
                    + " VALUES (?,?,?,?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, classCode);
            stm.setInt(2, classYear);
            stm.setString(3, classTerm);
            stm.setInt(4, block5);
            stm.setInt(5, status);
            stm.setInt(6, trainerId);
            stm.setInt(7, subjectId);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;

        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());

        }
        return false;
    }

    public String countClass() {
        String query = "select COUNT(class_id) as NumberOfClass from class";
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String number = rs.getString("NumberOfClass");
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
    
    public List<User> listTrainer() throws SQLException, Exception {
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
    
    public List<Class> searchClass(String status, String subjectID, String trainerID) throws ClassNotFoundException, SQLException, Exception {
        
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<Class> list = new ArrayList<>();
        String sql = "select class.class_id , class.class_code , class.class_year ,"
                + " class.class_term ,class.block5_class , class.status , user.full_name , subject.subject_code\n"
                + "from class\n"
                + "inner join user on class.trainer_id = user.user_id\n"
                + "inner join subject on class.subject_id = subject.subject_id\n";
        

        if (!status.equals("2")) {
            sql += " and class.status = '" + status + "'";
        }

        if (!subjectID.equals("0")) {
            sql += " and subject.subject_id = '" + subjectID + "' ";
        }

        if (!trainerID.equals("0")) {
            sql += " and user.user_id = '" + trainerID + "' ";
        }
        System.out.println(sql);
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Class(rs.getInt("class_id"), rs.getString("class_code"),
                        rs.getInt("class_year"), rs.getString("class_term"), rs.getInt("block5_class"),
                        rs.getInt("status"), rs.getString("full_name"), rs.getString("subject_code")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
