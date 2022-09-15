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
public class Criteria {

    private int criteriaID;
    private int evaluationWeight;
    private String teamEvaluation;
    private int criteriaOrder;
    private int max_lox;
    private int status;
    private int iterationID;
    private String iterationName;
    private int subjectID;
    private String subjectCode;
    private String description;
    private String criteriaName;

    public Criteria() {
    }

    public Criteria(int criteriaID, int evaluationWeight, String teamEvaluation, int criteriaOrder, int max_lox, int status, int iterationID, int subjectID, String description, String criteriaName) {
        this.criteriaID = criteriaID;
        this.evaluationWeight = evaluationWeight;
        this.teamEvaluation = teamEvaluation;
        this.criteriaOrder = criteriaOrder;
        this.max_lox = max_lox;
        this.status = status;
        this.iterationID = iterationID;
        this.subjectID = subjectID;
        this.description = description;
        this.criteriaName = criteriaName;
    }

  
    

    public Criteria(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public Criteria(int criteriaID, int evaluationWeight, String teamEvaluation, int criteriaOrder, int max_lox, int status, int iterationID, String iterationName) {
        this.criteriaID = criteriaID;
        this.evaluationWeight = evaluationWeight;
        this.teamEvaluation = teamEvaluation;
        this.criteriaOrder = criteriaOrder;
        this.max_lox = max_lox;
        this.status = status;
        this.iterationID = iterationID;
        this.iterationName = iterationName;
    }

    public Criteria(int criteriaID, int evaluationWeight, String teamEvaluation, int criteriaOrder, int max_lox, int status, int iterationID) {
        this.criteriaID = criteriaID;
        this.evaluationWeight = evaluationWeight;
        this.teamEvaluation = teamEvaluation;
        this.criteriaOrder = criteriaOrder;
        this.max_lox = max_lox;
        this.status = status;
        this.iterationID = iterationID;
    }
    

    public Criteria(int criteriaID, int evaluationWeight, String teamEvaluation, int criteriaOrder, int max_lox, int status, String iterationName,String subjectName,String description,String criteriaName) {
        this.criteriaID = criteriaID;
        this.evaluationWeight = evaluationWeight;
        this.teamEvaluation = teamEvaluation;
        this.criteriaOrder = criteriaOrder;
        this.max_lox = max_lox;
        this.status = status;
        this.iterationName = iterationName;
        this.subjectCode = subjectName;
        this.description = description;
        this.criteriaName = criteriaName;
    }

    public Criteria(int iterationID, String iterationName) {
        this.iterationID = iterationID;
        this.iterationName = iterationName;
    }

  
    

    public Criteria(int evaluationWeight, String teamEvaluation, int criteriaOrder, int max_lox, int status, String iterationName) {
        this.evaluationWeight = evaluationWeight;
        this.teamEvaluation = teamEvaluation;
        this.criteriaOrder = criteriaOrder;
        this.max_lox = max_lox;
        this.status = status;
        this.iterationName = iterationName;
    }

    public int getCriteriaID() {
        return criteriaID;
    }

    public void setCriteriaID(int criteriaID) {
        this.criteriaID = criteriaID;
    }

    public int getEvaluationWeight() {
        return evaluationWeight;
    }

    public void setEvaluationWeight(int evaluationWeight) {
        this.evaluationWeight = evaluationWeight;
    }

    public String getTeamEvaluation() {
        return teamEvaluation;
    }

    public void setTeamEvaluation(String teamEvaluation) {
        this.teamEvaluation = teamEvaluation;
    }

    public int getCriteriaOrder() {
        return criteriaOrder;
    }

    public void setCriteriaOrder(int criteriaOrder) {
        this.criteriaOrder = criteriaOrder;
    }

    public int getMax_lox() {
        return max_lox;
    }

    public void setMax_lox(int max_lox) {
        this.max_lox = max_lox;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIterationID() {
        return iterationID;
    }

    public void setIterationID(int iterationID) {
        this.iterationID = iterationID;
    }

    public String getIterationName() {
        return iterationName;
    }

    public void setIterationName(String iterationName) {
        this.iterationName = iterationName;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }
    

    @Override
    public String toString() {
        return "Criteria{" + "criteriaID=" + criteriaID + ", evaluationWeight=" + evaluationWeight + ", teamEvaluation=" + teamEvaluation + ", criteriaOrder=" + criteriaOrder + ", max_lox=" + max_lox + ", status=" + status + ", iterationID=" + iterationID + ", iterationName=" + iterationName + '}';
    }
    

}
