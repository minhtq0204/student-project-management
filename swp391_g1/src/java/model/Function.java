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
public class Function {
    private int functionID;
    private String functionName;
    private String accessRoles;
    private String description;
    private int complexityID;
    private int priority;
    private int status;
    private int teamID;
    private String teamName;
    private int featureID;
    private String featureName;
    private int ownerID;
    private String ownerName;

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

    public String getAccessRoles() {
        return accessRoles;
    }

    public void setAccessRoles(String accessRoles) {
        this.accessRoles = accessRoles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getComplexityID() {
        return complexityID;
    }

    public void setComplexityID(int complexityID) {
        this.complexityID = complexityID;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Function(int functionID, String functionName, String accessRoles, String description, int complexityID, int priority, int status, int teamID, String teamName, int featureID, String featureName, int ownerID, String ownerName) {
        this.functionID = functionID;
        this.functionName = functionName;
        this.accessRoles = accessRoles;
        this.description = description;
        this.complexityID = complexityID;
        this.priority = priority;
        this.status = status;
        this.teamID = teamID;
        this.teamName = teamName;
        this.featureID = featureID;
        this.featureName = featureName;
        this.ownerID = ownerID;
        this.ownerName = ownerName;
    }

    public Function(String functionName, String accessRoles, String description, int complexityID, int priority, int status, int teamID, String teamName, int featureID, String featureName, int ownerID, String ownerName) {
        this.functionName = functionName;
        this.accessRoles = accessRoles;
        this.description = description;
        this.complexityID = complexityID;
        this.priority = priority;
        this.status = status;
        this.teamID = teamID;
        this.teamName = teamName;
        this.featureID = featureID;
        this.featureName = featureName;
        this.ownerID = ownerID;
        this.ownerName = ownerName;
    }

    public Function(int functionID, String functionName, String accessRoles, String description, int complexityID, int priority, int status, int teamID, int featureID, int ownerID) {
        this.functionID = functionID;
        this.functionName = functionName;
        this.accessRoles = accessRoles;
        this.description = description;
        this.complexityID = complexityID;
        this.priority = priority;
        this.status = status;
        this.teamID = teamID;
        this.featureID = featureID;
        this.ownerID = ownerID;
    }

    public Function(int functionID, String functionName, String accessRoles, String description, int complexityID, int priority, int status, String teamName, String featureName, String ownerName) {
        this.functionID = functionID;
        this.functionName = functionName;
        this.accessRoles = accessRoles;
        this.description = description;
        this.complexityID = complexityID;
        this.priority = priority;
        this.status = status;
        this.teamName = teamName;
        this.featureName = featureName;
        this.ownerName = ownerName;
    }

    public Function() {
    }

    @Override
    public String toString() {
        return "Function{" + "functionID=" + functionID + ", functionName=" + functionName + ", accessRoles=" + accessRoles + ", description=" + description + ", complexityID=" + complexityID + ", priority=" + priority + ", status=" + status + ", teamID=" + teamID + ", teamName=" + teamName + ", featureID=" + featureID + ", featureName=" + featureName + ", ownerID=" + ownerID + ", ownerName=" + ownerName + '}';
    }
    
    
    
}
