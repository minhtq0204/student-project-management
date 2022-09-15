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
public class Issue {
    private int issueID;
    private String issueTitle;
    private String description; 
    private String dua_date;
    private String createAt;
    private String gitlabURL;
    private int functionID;
    private String functionName;
    private String labels;
    private int status;
    private int assignID;
    private String assignName;
    private int teamID;
    private String teamName;

    public Issue() {
    }

    public Issue(String issueTitle, String description, String dua_date, String gitlabURL, String functionName, int status, String assignName, String teamName) {
        this.issueTitle = issueTitle;
        this.description = description;
        this.dua_date = dua_date;
        this.gitlabURL = gitlabURL;
        this.functionName = functionName;
        this.status = status;
        this.assignName = assignName;
        this.teamName = teamName;
    }
    

    public Issue(int issueID, String issueTitle, String description, String dua_date, String createAt, String gitlabURL, int functionID, int status, int assignID, int teamID) {
        this.issueID = issueID;
        this.issueTitle = issueTitle;
        this.description = description;
        this.dua_date = dua_date;
        this.createAt = createAt;
        this.gitlabURL = gitlabURL;
        this.functionID = functionID;
        this.status = status;
        this.assignID = assignID;
        this.teamID = teamID;
    }

    public Issue(int issueID, String issueTitle, String description, String dua_date, String createAt, String gitlabURL, int functionID, String functionName, String labels, int status, int assignID, String assignName, int teamID, String teamName) {
        this.issueID = issueID;
        this.issueTitle = issueTitle;
        this.description = description;
        this.dua_date = dua_date;
        this.createAt = createAt;
        this.gitlabURL = gitlabURL;
        this.functionID = functionID;
        this.functionName = functionName;
        this.labels = labels;
        this.status = status;
        this.assignID = assignID;
        this.assignName = assignName;
        this.teamID = teamID;
        this.teamName = teamName;
    }

    public Issue(int issueID, String issueTitle, String description, String dua_date, String createAt, String gitlabURL, String functionName, String labels, int status, String assignName, String teamName) {
        this.issueID = issueID;
        this.issueTitle = issueTitle;
        this.description = description;
        this.dua_date = dua_date;
        this.createAt = createAt;
        this.gitlabURL = gitlabURL;
        this.functionName = functionName;
        this.labels = labels;
        this.status = status;
        this.assignName = assignName;
        this.teamName = teamName;
    }

    public Issue(int issueID, String issueTitle, String description, String createAt, String gitlabURL, String functionName, int status, String assignName, String teamName) {
        this.issueID = issueID;
        this.issueTitle = issueTitle;
        this.description = description;
        this.createAt = createAt;
        this.gitlabURL = gitlabURL;
        this.functionName = functionName;
        this.status = status;
        this.assignName = assignName;
        this.teamName = teamName;
    }

    public Issue(String issueTitle, String description, String gitlabURL, int functionID, String functionName, int status, int assignID, String assignName, int teamID, String teamName) {
        this.issueTitle = issueTitle;
        this.description = description;
        this.gitlabURL = gitlabURL;
        this.functionID = functionID;
        this.functionName = functionName;
        this.status = status;
        this.assignID = assignID;
        this.assignName = assignName;
        this.teamID = teamID;
        this.teamName = teamName;
    }
    

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDua_date() {
        return dua_date;
    }

    public void setDua_date(String dua_date) {
        this.dua_date = dua_date;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }


    public String getGitlabURL() {
        return gitlabURL;
    }

    public void setGitlabURL(String gitlabURL) {
        this.gitlabURL = gitlabURL;
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

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAssignID() {
        return assignID;
    }

    public void setAssignID(int assignID) {
        this.assignID = assignID;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
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

    @Override
    public String toString() {
        return "Issue{" + "issueID=" + issueID + ", issueTitle=" + issueTitle + ", description=" + description + ", dua_date=" + dua_date + ", createAt=" + createAt + ", gitlabURL=" + gitlabURL + ", functionID=" + functionID + ", functionName=" + functionName + ", labels=" + labels + ", status=" + status + ", assignID=" + assignID + ", assignName=" + assignName + ", teamID=" + teamID + ", teamName=" + teamName + '}';
    }
    
    
}
