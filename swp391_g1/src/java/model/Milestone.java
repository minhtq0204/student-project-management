/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author minhc
 */
public class Milestone {
    private int milestoneId ,status,iterationId,classId ;
    private String fromDate ,toDate;
    private String iterationName ,classCode, milestoneName, description;

    public Milestone() {
    }

    public Milestone(int milestoneId, int status, int iterationId, int classId, String fromDate, String toDate, String iterationName, String classCode, String milestoneName, String description) {
        this.milestoneId = milestoneId;
        this.status = status;
        this.iterationId = iterationId;
        this.classId = classId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.iterationName = iterationName;
        this.classCode = classCode;
        this.milestoneName = milestoneName;
        this.description = description;
    }

    

    
    

    public int getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(int milestoneId) {
        this.milestoneId = milestoneId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIterationId() {
        return iterationId;
    }

    public void setIterationId(int iterationId) {
        this.iterationId = iterationId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getIterationName() {
        return iterationName;
    }

    public void setIterationName(String iterationName) {
        this.iterationName = iterationName;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\n MileStone{" + "milestoneId=" + milestoneId + ", status=" + status + ", iterationId=" + iterationId + ", classId=" + classId + ", fromDate=" + fromDate + ", toDate=" + toDate + ", iterationName=" + iterationName + ", classCode=" + classCode + ", milestoneName=" + milestoneName + ", description=" + description + '}';
    }
    
    
    

    
    
    
    
}
