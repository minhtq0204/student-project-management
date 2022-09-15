/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author minhc
 */
public class Feature {
    private int featureID;
    private String featureName;
    private int status;
    private int teamID;
    private String topicName;
    private int classID;
    private String className;

    public Feature() {
    }

    public int getFeatureID() {
        return featureID;
    }

    public void setFeatureID(int featureID) {
        this.featureID = featureID;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Feature(int featureID, String featureName, int status) {
        this.featureID = featureID;
        this.featureName = featureName;
        this.status = status;
    }

    public Feature(int featureID, String featureName, int status, String topicName, String className) {
        this.featureID = featureID;
        this.featureName = featureName;
        this.status = status;
        this.topicName = topicName;
        this.className = className;
    }

    public Feature(int featureID, String featureName, int status, int teamID, int classID) {
        this.featureID = featureID;
        this.featureName = featureName;
        this.status = status;
        this.teamID = teamID;
        this.classID = classID;
    }
    
    

   
    
    
}
