/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author minhq
 */
public class User {

    private int userId;
    private String roll_number;
    private String full_name;
    private int gender;
    private String dateOfBirth;
    private String email;
    private String mobile;
    private String avatar_link;
    private String face_link;
    private int role_id;
    private int status;
    private String password;
    
    public User() {
    }

    public User(String full_name, String email, String password, int gender, String mobile) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.mobile = mobile;
    }

    public User(int userId, String roll_number, String full_name, int gender, String dateOfBirth, String email, String mobile, String avatar_link, String face_link, int role_id, int status, String password) {
        this.userId = userId;
        this.roll_number = roll_number;
        this.full_name = full_name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.mobile = mobile;
        this.avatar_link = avatar_link;
        this.face_link = face_link;
        this.role_id = role_id;
        this.status = status;
        this.password = password;
    }

    public User(int userId, String full_name, int gender, String dateOfBirth, String email, String mobile, String avatar_link, String face_link, int role_id, int status, String password) {
        this.userId = userId;
        this.full_name = full_name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.mobile = mobile;
        this.avatar_link = avatar_link;
        this.face_link = face_link;
        this.role_id = role_id;
        this.status = status;
        this.password = password;
    }
    
    public User(int userId, String full_name) {
        this.userId = userId;
        this.full_name = full_name;
    }

    public User(int userId, String full_name, int role_id, int status) {
        this.userId = userId;
        this.full_name = full_name;
        this.role_id = role_id;
        this.status = status;
    }

    public User(int role_id) {
        this.role_id = role_id;
    }
    
    

    public User(String full_name) {
        this.full_name = full_name;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getAvatar_link() {
        return avatar_link;
    }

    public void setAvatar_link(String avatar_link) {
        this.avatar_link = avatar_link;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String phone) {
        this.mobile = mobile;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFace_link() {
        return face_link;
    }

    public void setFace_link(String face_link) {
        this.face_link = face_link;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", roll_number=" + roll_number + ", full_name=" + full_name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", mobile=" + mobile + ", avatar_link=" + avatar_link + ", face_link=" + face_link + ", role_id=" + role_id + ", status=" + status + ", password=" + password + '}';
    }
    
}
