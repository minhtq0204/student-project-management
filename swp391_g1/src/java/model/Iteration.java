/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Iteration {

    private int iterationID;
    private String iterationName;
    private String duration;
    private int status;
    private int subjectID;
    private String description;

     private String subjectCode;



    public Iteration() {
    }

    public Iteration(int iterationID, int subjectID) {
        this.iterationID = iterationID;
        this.subjectID = subjectID;
    }

    public Iteration(int iterationID, String iterationName) {
        this.iterationID = iterationID;
        this.iterationName = iterationName;
    }

    public Iteration(int iterationID, String iterationName, int status, int subjectID, String subjectCode, String description, String duration) {
        this.iterationID = iterationID;
        this.iterationName = iterationName;
        this.status = status;
        this.subjectID = subjectID;
        this.subjectCode = subjectCode;
        this.description = description;
        this.duration = duration;
    }
    
    public Iteration(int iterationID, String iterationName, String duration, int status, int subjectID,
            String subjectCode, String description){
        this.iterationID = iterationID;
        this.iterationName = iterationName;
        this.duration = duration;
        this.status = status;
        this.subjectID = subjectID;
        this.subjectCode = subjectCode;
        this.description = description;
    }
    
    public Iteration(int iterationID, String iterationName, String duration, 
            int status, int subjectID, String description){
        this.iterationID = iterationID;
        this.iterationName = iterationName;
        this.duration = duration;
        this.status = status;
        this.subjectID = subjectID;
        this.description = description;
    }
    

    public int getIterationID() {
        return iterationID;
    }

    public void setIterationID(int iterationID) {
        this.iterationID = iterationID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getIterationName() {
        return iterationName;
    }

    public void setIterationName(String iterationName) {
        this.iterationName = iterationName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    

}
