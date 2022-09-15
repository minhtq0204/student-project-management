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
import model.Setting;
import model.Type;

/**
 *
 * @author mac
 */
public class SettingDAO {

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

    public boolean checkDupSettingValue(String settingValue) {
        boolean check = false;
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "SELECT * from setting where setting_value = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, settingValue);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            CloseConnection();
        } catch (Exception ex) {

        }
        return check;
    }

    public List<Type> listType() throws SQLException, Exception {
        List<Type> listType = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select * from type";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("type_id");
                String name = rs.getString("name");
                Type type = new Type(id, name);
                listType.add(type);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listType;
    }

    public List<Setting> getListSetting(int index) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<Setting> list = new ArrayList<>();
        String sql = "select setting.setting_id, type.name, setting.setting_value, setting.setting_note,"
                + " setting.status, type.type_id from setting"
                + " join type on setting.type_id = type.type_id \n"
                + "ORDER BY setting_id limit 5 OFFSET " + (index - 1) * 5 + "";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Setting(rs.getInt("setting_id"), rs.getString("setting_value"), rs.getString("setting_note"),
                        rs.getInt("status"), rs.getInt("type_id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String countSetting() {
        String query = "select COUNT(setting_id) as NumberOfSetting from setting";
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String number = rs.getString("NumberOfSetting");
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

    public Setting getSettingByID(int id) throws Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        String query = "select * from setting where setting_id = ?";
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                return new Setting(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean createNewSetting(String value, String note, int status, int typeId) {

        try {
            String sql = "INSERT INTO setting (setting_value,"
                    + " setting_note, status, type_id) VALUES (?,?,?,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, value);
            stm.setString(2, note);
            stm.setInt(3, status);
            stm.setInt(4, typeId);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;
        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());
        }
        return false;
    }

    // update setting
    public boolean updateSetting(int settingId, String value, String note,
            int status, int typeId) {

        try {
            String sql = "UPDATE setting SET"
                    + " setting_value = ? ,"
                    + " setting_note = ? ,"
                    + " type_id = ?, status = ? WHERE setting_id= ?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);

            stm.setString(1, value);
            stm.setString(2, note);
            stm.setInt(3, typeId);
            stm.setInt(4, status);
            stm.setInt(5, settingId);
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

    public boolean changeStatusSetting(int id) throws Exception {
        boolean check = false;
        Setting accCheck = new Setting();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql1 = "select * from setting where setting_id = ?";
            stm = conn.prepareStatement(sql1);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                accCheck = new Setting(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));

            }
            CloseConnection();
        } finally {
            CloseConnection();
        }

        try {
            conn = new SQLServerConnection().getConnection();
            String sqltrue = "UPDATE setting set status=1 where setting_id = ?";
            String sqlfalse = "UPDATE setting set status=0 where setting_id = ?";
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

    public boolean checkDup(String value) {
        boolean check = false;
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "SELECT * from setting where setting_value =?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, value);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            CloseConnection();
        } catch (Exception ex) {

        }
        return check;
    }

    public List<Setting> searchSetting(String status, String typeId, String txtSearch) throws ClassNotFoundException, SQLException, Exception {
        SQLServerConnection sqlCon = new SQLServerConnection();
        List<Setting> list = new ArrayList<>();
        String sql = "select setting.setting_id, type.name, setting.setting_value, setting.setting_note,"
                + " setting.status, type.type_id from setting"
                + " join type on setting.type_id = type.type_id";

        if (!status.equals("2")) {
            sql += " and setting.status = '" + status + "'";
        }

        if (!typeId.equals("0")) {
            sql += " and type.type_id = '" + typeId + "' ";
        }

        if (txtSearch != null) {
            sql += " and setting.setting_value like '%" + txtSearch + "%'";
        }
        System.out.println(sql);
        try {
            conn = sqlCon.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Setting(rs.getInt("setting_id"), rs.getString("setting_value"), rs.getString("setting_note"),
                        rs.getInt("status"), rs.getInt("type_id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws SQLException, Exception {
        SettingDAO x = new SettingDAO();

    }
}
