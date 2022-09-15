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
public class Tracking {
    private int trackingID;
    private int assignerID;
    private String assignerName;
    private int assigneeID;
    private String assigneeName;
    private int status;
    private String trackingNote;
    private String update ;
    private int teamID;
    private String teamName;
    private int functionID;
    private String functionName;
    private int milestoneID;
    private String milestoneName;
    private String description;

    public Tracking() {
    }

    public Tracking(int trackingID,String assignerName, String assigneeName, int status, String trackingNote, String update, String teamName, String functionName, String milestoneName, String description) {
        this.trackingID = trackingID;
        this.assignerName = assignerName;
        this.assigneeName = assigneeName;
        this.status = status;
        this.trackingNote = trackingNote;
        this.update = update;
        this.teamName = teamName;
        this.functionName = functionName;
        this.milestoneName = milestoneName;
        this.description = description;
    }
    

    public int getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(int trackingID) {
        this.trackingID = trackingID;
    }

    public int getAssignerID() {
        return assignerID;
    }

    public void setAssignerID(int assignerID) {
        this.assignerID = assignerID;
    }

    public String getAssignerName() {
        return assignerName;
    }

    public void setAssignerName(String assignerName) {
        this.assignerName = assignerName;
    }

    public int getAssigneeID() {
        return assigneeID;
    }

    public void setAssigneeID(int assigneeID) {
        this.assigneeID = assigneeID;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTrackingNote() {
        return trackingNote;
    }

    public void setTrackingNote(String trackingNote) {
        this.trackingNote = trackingNote;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getFunctionID() {
        return functionID;
    }

    public void setFunctionID(int functionID) {
        this.functionID = functionID;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public int getMilestoneID() {
        return milestoneID;
    }

    public void setMilestoneID(int milestoneID) {
        this.milestoneID = milestoneID;
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
    
}
