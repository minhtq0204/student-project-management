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

import model.User;

/**
 *
 * @author Thanh
 */
public class UserDAO {

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

    public User getOneAccount(String username, String password) throws ClassNotFoundException, SQLException, Exception {
        User y = null;
        SQLServerConnection sqlCon = new SQLServerConnection();
        try {
            conn = sqlCon.getConnection();
            String sql = "select * from user where email= ? and password = ? and status = 1";
            System.out.print(username);
            System.out.print(password);

            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            while (rs.next()) {

                User x = new User(rs.getInt("user_id"), rs.getString("roll_number"),
                        rs.getString("full_name"), rs.getInt("gender"),
                        rs.getString("date_of_birth"), rs.getString("email"),
                        rs.getString("mobile"), rs.getString("avatar_link"),
                        rs.getString("face_link"), rs.getInt("role_id"),
                        rs.getInt("status"), rs.getString("password"));
//                System.out.println(sql);
                return x;

            }
            CloseConnection();
        } finally {
            CloseConnection();
        }
        return y;
    }

    public boolean createNewUser(String full_name, String email, String password, int gender, String mobile) {

        try {
            String sql = "INSERT INTO user (full_name,email,password,gender,status,mobile) VALUES (?,?,?,?,1,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, full_name);
            stm.setString(2, email);
            stm.setString(3, password);
            stm.setInt(4, gender);
            stm.setString(5, mobile);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;
        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());

        }
        return false;
    }

    public boolean checkDup(String email) {
        boolean check = false;
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "SELECT * from user where email =?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            CloseConnection();
        } catch (Exception ex) {

        }
        return check;
    }

    public List<User> list() throws SQLException, Exception {
        List<User> listAuthor = new ArrayList<>();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "select * from user where role_id='2' and status='1' order by full_name;";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("full_name");
                User user = new User(id, name);
                listAuthor.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listAuthor;
    }

    public boolean changePass(String password, int id) {
        try {
            conn = new SQLServerConnection().getConnection();
            String sql = "update user set password = '" + password + "' where user_id = " + id + "";
            stm = conn.prepareStatement(sql);
            stm.executeUpdate();
            CloseConnection();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    // get all user
    public List<User> getAllUsers(int index) {
        List<User> list = new ArrayList<>();
        String query = "select * from user\n"
                + " ORDER BY user_id limit 5 OFFSET " + (index - 1) * 5 + "";;
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("user_id"),
                        rs.getString("full_name"), rs.getInt("gender"),
                        rs.getString("date_of_birth"), rs.getString("email"),
                        rs.getString("mobile"), rs.getString("avatar_link"),
                        rs.getString("face_link"), rs.getInt("role_id"),
                        rs.getInt("status"), rs.getString("password")));
            }
            stm.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public String countUser() {
        String query = "select COUNT(user_id) as NumberOfUser from user";
        try {
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String number = rs.getString("NumberOfUser");
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

    //search user
    public List<User> searchUser(String role, String status) {
        List<User> list = new ArrayList<>();
        try {
            String sql = "select * from user where email is not null";
            if (!status.equals("2")) {
                sql += " and status = " + status + "";
            }

            if (!role.equals("4")) {
                sql += " and role_id = " + role + "";
            }

            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("user_id"),
                        rs.getString("full_name"), rs.getInt("gender"),
                        rs.getString("date_of_birth"), rs.getString("email"),
                        rs.getString("mobile"), rs.getString("avatar_link"),
                        rs.getString("face_link"), rs.getInt("role_id"),
                        rs.getInt("status"), rs.getString("password")));
            }
            stm.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        for (User x : list) {
            System.out.println(x.toString());
        }
        return list;
    }

    // update status of user
    public boolean updateUserStatusId(int userId, int statusId) {

        try {
            String sql = "update user set status=? where user_id=?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, statusId);
            stm.setInt(2, userId);
            stm.executeUpdate();
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // create user from admin dashboard
    public boolean createNewUserFromAdmin(String roll_number, String full_name, int gender,
            String dob, String email, String mobile, String avatar_link,
            String face_link, int role_id, String password) {

        try {
            String sql = "INSERT INTO user (roll_number, full_name, email, password, gender,"
                    + " date_of_birth, role_id, avatar_link, face_link, status, mobile) VALUES (?,?,?,?,?,?,?,?,?,1,?)";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, roll_number);
            stm.setString(2, full_name);
            stm.setString(3, email);
            stm.setString(4, password);
            stm.setInt(5, gender);
            stm.setString(6, dob);
            stm.setInt(7, role_id);
            stm.setString(8, avatar_link);
            stm.setString(9, face_link);
            stm.setString(10, mobile);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Insert OK");
            return true;
        } catch (Exception e) {
            System.out.println("Insert fail" + e.getMessage());

        }
        return false;

    }

    // update user from admin dashboard
    public boolean updateUser(int userId, String roll_number, String full_name,
            int gender, String dob, String email,
            String mobile, String avatar_link, String facebook_link,
            int roleID, int statusID) {

        try {
            String sql = "UPDATE user SET roll_number = ?, full_name = ?, gender = ? ,date_of_birth = ?, "
                    + "email = ?, mobile = ?, "
                    + "avatar_link = ?, face_link = ?, "
                    + "role_id = ?, status = ? WHERE user_id= ?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);

            stm.setString(1, roll_number);
            stm.setString(2, full_name);
            stm.setInt(3, gender);
            stm.setString(4, dob);
            stm.setString(5, email);
            stm.setString(6, mobile);
            stm.setString(7, avatar_link);
            stm.setString(8, facebook_link);
            stm.setInt(9, roleID);
            stm.setInt(10, statusID);
            stm.setInt(11, userId);
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

    // delete user
    public boolean removeUserByUserId(int userId) {

        try {
            String sql = "DELETE FROM user WHERE user_id=?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.executeUpdate();
            System.out.println(sql);
            System.out.println("Delete OK");
            stm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Delete fail " + e.getMessage());
            return false;
        }
    }

    // get user by user id
    public User getUserById(int id) {
        try {
            String sql = "select * from user where user_id=?";
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                User x = new User(rs.getInt("user_id"), rs.getString("roll_number"),
                        rs.getString("full_name"), rs.getInt("gender"),
                        rs.getString("date_of_birth"), rs.getString("email"),
                        rs.getString("mobile"), rs.getString("avatar_link"),
                        rs.getString("face_link"), rs.getInt("role_id"),
                        rs.getInt("status"), rs.getString("password"));
                stm.close();
                rs.close();
                return x;
            }
            stm.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean updateStatus(int Status, int userID) {
        try {
            String sql = "";
            if (Status == 1) {
                sql = "update user set status = 0 where user.user_id = " + userID + "";
            } else {
                sql = "update user set status = 1 where user.user_id = " + userID + "";
            }
            conn = new SQLServerConnection().getConnection();
            stm = conn.prepareStatement(sql);
            stm.executeUpdate();
            System.out.println("Update OK");

            stm.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Update fail" + e.getMessage());
        }
        return false;
    }
    
    public boolean changeStatusUser(int id) throws Exception {
        boolean check = false;
        User user = new User();
        try {
            conn = new SQLServerConnection().getConnection();
            String sql1 = "select * from user where user_id = ?";
            stm = conn.prepareStatement(sql1);
            stm.setObject(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("roll_number"),
                        rs.getString("full_name"), rs.getInt("gender"),
                        rs.getString("date_of_birth"), rs.getString("email"),
                        rs.getString("mobile"), rs.getString("avatar_link"),
                        rs.getString("face_link"), rs.getInt("role_id"),
                        rs.getInt("status"), rs.getString("password"));
            }
            CloseConnection();
        } finally {
            CloseConnection();
        }

        try {
            conn = new SQLServerConnection().getConnection();
            String sqltrue = "UPDATE user set status=1 where user_id = ?";
            String sqlfalse = "UPDATE user set status=0 where user_id = ?";
            if (user.getStatus() == 0) {
                stm = conn.prepareStatement(sqltrue);
                stm.setObject(1, id);
                int a = stm.executeUpdate();
                check = a > 0 ? true : false;
                CloseConnection();
            } else if (user.getStatus() == 1) {
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

    public static void main(String[] args) throws SQLException, Exception {
        UserDAO x = new UserDAO();

    }

}


