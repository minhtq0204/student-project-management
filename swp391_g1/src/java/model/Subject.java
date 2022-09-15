/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DELL
 */
public class Subject {

    private int subjectID;
    private String subjectCode;
    private String subjectName;
    private int authorID;
    private int status;
    private String fullName;
    private String description;

    public Subject() {
    }

    public Subject(int subjectID, String subjectCode) {
        this.subjectID = subjectID;
        this.subjectCode = subjectCode;
    }

    public Subject(String subjectCode, String subjectName, int authorID, int status, String description) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.authorID = authorID;
        this.status = status;
        this.description = description;
    }

    public Subject(int subjectID, String subjectCode, String subjectName, int authorID, int status, String description) {
        this.subjectID = subjectID;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.authorID = authorID;
        this.status = status;
        this.description = description;
    }

    public Subject(int subjectID, String subjectCode, String subjectName, int status, String fullName, String description) {
        this.subjectID = subjectID;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.status = status;
        this.fullName = fullName;
        this.description = description;
    }


    public Subject(int subjectID, String subjectCode, String subjectName, int authorID, int status, String fullName, String description) {
        this.subjectID = subjectID;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.authorID = authorID;
        this.status = status;
        this.fullName = fullName;
        this.description = description;
    }


    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Subject{" + "subjectID=" + subjectID + ", subjectCode=" + subjectCode + ", subjectName=" + subjectName + ", authorID=" + authorID + ", status=" + status + ", fullName=" + fullName + ", description=" + description + '}';
    }

    
}
