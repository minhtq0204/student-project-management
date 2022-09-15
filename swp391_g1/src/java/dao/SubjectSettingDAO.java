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
import model.SubjectSetting;
import model.Type;

/**
 *
 * @author mac
 */
public class SubjectSettingDAO {

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

    public ArrayList<SubjectSetting> getListSubjectSetting(int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        ArrayList<SubjectSetting> list = new ArrayList<>();
        String sql = "select subject_setting.setting_id, subject.subject_name, type.name,subject_setting.setting_title,subject_setting.setting_value,  subject_setting.status\n"
                + "from subject_setting\n"
                + "inner join subject on subject_setting.subject_id = subject.subject_id\n"
                + "inner join type on subject_setting.type_id = type.type_id\n"
                + "ORDER BY setting_id limit 5 OFFSET " + (index - 1) * 5 + "";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new SubjectSetting(rs.getInt("setting_id"), rs.getString("setting_title"), rs.getString("setting_title"), rs.getInt("status"), rs.getString("subject_name"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String countSubjectSetting() {
        String query = "select COUNT(setting_id) as NumberOfSubjectSetting from subject_setting";
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String number = rs.getString("NumberOfSubjectSetting");
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
            String sql = "select subject_id,subject_name\n"
                    + "from subject\n"
                    + "where subject.status= 1";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("subject_id");
                String name = rs.getString("subject_name");
                Subject subject = new Subject();
                subject.setSubjectID(id);
                subject.setSubjectName(name);
                listSubject.add(subject);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listSubject;
    }

    public List<Type> listType() throws SQLException, Exception {
        List<Type> listType = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select type_id,name\n"
                    + "from type\n";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("type_id");
                String name = rs.getString("name");
                Type type = new Type();
                type.setTypeId(id);
                type.setName(name);
                listType.add(type);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listType;
    }

    public SubjectSetting getSubjectSettingByID(int settingID) throws Exception {

        String query = "select * from subject_setting where setting_id = ?";
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            stm.setInt(1, settingID);
            rs = stm.executeQuery();
            while (rs.next()) {
                SubjectSetting subjectSetting = new SubjectSetting(rs.getInt("setting_id"), rs.getString("setting_title"), rs.getString("setting_value"), rs.getInt("status"), rs.getInt("subject_id"), rs.getInt("type_id"), rs.getString("description"));
                stm.close();
                rs.close();
                return subjectSetting;
            }
            stm.close();
            rs.close();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public void deleteSubjectSetting(String id) {
        SQLServerConnection sqlCon = new SQLServerConnection();
        String sql = "delete from subject_setting where setting_id = ?";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public boolean createNewSubjectSetting(String setting_title, String setting_value, int status, int subjectId, int typeID, String description) {
        //int displayOrder, String settingTitle, String settingValue, int status, int subjectId
        try {
            String sql = "INSERT INTO subject_setting (setting_title,setting_value,status,subject_id,type_id,description) VALUES (?,?,?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, setting_title);
            stm.setString(2, setting_value);
            stm.setInt(3, status);
            stm.setInt(4, subjectId);
            stm.setInt(5, typeID);
            stm.setString(6, description);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;
        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());
        }
        return false;
    }

    public boolean updateSubjectSetting(int settingID, String setting_title, String setting_value, int status, int subjectId, int typeID, String description) {

        try {
            String sql = "update subject_setting set setting_title=?,setting_value =?, status = ?, subject_id = ?, type_id =?, description=?  where setting_id=?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, setting_title);
            stm.setString(2, setting_value);
            stm.setInt(3, status);
            stm.setInt(4, subjectId);
            stm.setInt(5, typeID);
            stm.setString(6, description);
            stm.setInt(7, settingID);
            stm.executeUpdate();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<SubjectSetting> searchByStatusSubjectSetting(String status) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<SubjectSetting> list = new ArrayList<>();
        String sql;
        if (status.equals("1")) {
            sql = "select * from subject_setting";
        } else {
            sql = "select * from subject_setting where status = ?";
        }
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, status);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new SubjectSetting(rs.getInt("setting_id"), rs.getString("setting_title"), rs.getString("setting_title"), rs.getInt("status"), rs.getString("subject_name"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean changeStatusSubjectSetting(int id) throws Exception {
        boolean check = false;
        SubjectSetting accCheck = new SubjectSetting();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql1 = "select * from subject_setting where setting_id = ?";
            stm = conn.prepareStatement(sql1);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                accCheck = new SubjectSetting(rs.getInt("setting_id"), rs.getString("setting_title"),
                        rs.getString("setting_value"), rs.getInt("status"), rs.getInt("subject_id"),
                        rs.getInt("type_id"), rs.getString("description"));

            }
            CloseConnection();
        } finally {
            CloseConnection();
        }

        try {
            conn = new SQLServerConnection().getConnection();
            String sqltrue = "UPDATE subject_setting set status=1 where setting_id = ?";
            String sqlfalse = "UPDATE subject_setting set status=0 where setting_id = ?";
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

    public List<SubjectSetting> searchSubjectSetting(String status, String subjectID,
            String typeID) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<SubjectSetting> list = new ArrayList<>();
        String sql = "select subject_setting.setting_id, subject.subject_name, type.name,subject_setting.setting_title,subject_setting.setting_value,  subject_setting.status\n"
                + "from subject_setting\n"
                + "inner join subject on subject_setting.subject_id = subject.subject_id\n"
                + "inner join type on subject_setting.type_id = type.type_id";

        if (!status.equals("2")) {
            sql += " and subject_setting.status = '" + status + "'";
        }

        if (!subjectID.equals("0")) {
            sql += " and subject.subject_id = '" + subjectID + "' ";
        }

        if (!typeID.equals("0")) {
            sql += " and type.type_id = '" + typeID + "' ";
        }

        System.out.println(sql);
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new SubjectSetting(rs.getInt("setting_id"), rs.getString("setting_title"), rs.getString("setting_title"), rs.getInt("status"), rs.getString("subject_name"), rs.getString("name")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        SubjectSettingDAO dao = new SubjectSettingDAO();
        List<Subject> list = dao.listSubject();
        for (Subject ss : list) {
            System.out.println(ss);
        }
    }
}
