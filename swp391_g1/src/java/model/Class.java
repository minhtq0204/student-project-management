/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Thanh
 */
public class Class {
    private int classId;
    private String classCode;
    private int classYear;
    private String classTerm;
    private int block5Class;
    private int status;
    private int trainerId;
    private String trainerName;
    private int subjectId;
    private String subjectCode;

    public Class(int classId, String classCode, int classYear, String classTerm, int block5Class, int status, int trainerId, int subjectId) {
        this.classId = classId;
        this.classCode = classCode;
        this.classYear = classYear;
        this.classTerm = classTerm;
        this.block5Class = block5Class;
        this.status = status;
        this.trainerId = trainerId;
        this.subjectId = subjectId;
    }
    
    

    public Class(int classId, String classCode, int classYear, String classTerm, int block5Class, int status, String trainerName, String subjectName) {
        this.classId = classId;
        this.classCode = classCode;
        this.classYear = classYear;
        this.classTerm = classTerm;
        this.block5Class = block5Class;
        this.status = status;
        this.trainerName = trainerName;
        this.subjectCode = subjectName;
    }

    

    public Class(int classId, String classCode, int status) {
        this.classId = classId;
        this.classCode = classCode;
        this.status = status;
    }
    
    

    public Class() {
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public int getClassYear() {
        return classYear;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    public String getClassTerm() {
        return classTerm;
    }

    public void setClassTerm(String classTerm) {
        this.classTerm = classTerm;
    }

    public int getBlock5Class() {
        return block5Class;
    }

    public void setBlock5Class(int block5Class) {
        this.block5Class = block5Class;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
     
}
