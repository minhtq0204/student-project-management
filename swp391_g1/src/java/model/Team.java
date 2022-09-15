/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mac
 */
public class Team {

    private int teamID;
    private String teamName;
    private String topicCode;
    private String topicName;
    private String gitURL;
    private String description;
    private int status;
    private int classID;
    private String classCode;

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Team() {
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(int teamID, String topicCode) {
        this.teamID = teamID;
        this.topicCode = topicCode;
    }

   
    
    

    public Team(int teamID, String teamName, String topicCode, String topicName, String gitURL, String description,
            int status, int classID, String classCode) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.topicCode = topicCode;
        this.topicName = topicName;
        this.gitURL = gitURL;
        this.description = description;
        this.status = status;
        this.classID = classID;
        this.classCode = classCode;
    }

    public Team(String teamName, String topicCode, String topicName, String gitURL, String description,
            int status, int classID) {
        this.teamName = teamName;
        this.topicCode = topicCode;
        this.topicName = topicName;
        this.gitURL = gitURL;
        this.description = description;
        this.status = status;
        this.classID = classID;
    }

    public Team(int teamID, String teamName, String topicCode, String topicName) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.topicCode = topicCode;
        this.topicName = topicName;
    }

    public Team(int teamID, String teamName, String topicCode, String topicName, String gitURL, String description,
            int status, int classID) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.topicCode = topicCode;
        this.topicName = topicName;
        this.gitURL = gitURL;
        this.description = description;
        this.status = status;
        this.classID = classID;
    }

    public Team(String teamName, String topicCode, String topicName, String gitURL, String description, int status, int classID, String classCode) {
        this.teamName = teamName;
        this.topicCode = topicCode;
        this.topicName = topicName;
        this.gitURL = gitURL;
        this.description = description;
        this.status = status;
        this.classID = classID;
        this.classCode = classCode;
    }

    public Team(int teamID, String teamName, int status) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.status = status;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getGitURL() {
        return gitURL;
    }

    public void setGitURL(String gitURL) {
        this.gitURL = gitURL;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Team{" + "teamID=" + teamID + ", teamName=" + teamName + ", topicCode=" + topicCode + ", topicName=" + topicName + ", gitURL=" + gitURL + ", description=" + description + ", status=" + status + ", classID=" + classID + ", classCode=" + classCode + '}';
    }

    

}
