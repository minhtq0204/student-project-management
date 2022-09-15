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
public class SubjectSetting {
      private int settingID;
      private String settingTitle;
      private String settingValue ;
      private int status;
      private int subjectID;
      private String subjectName;
      private int typeID;
      private String tyName;
      private String description;

    public SubjectSetting() {
    }

    public SubjectSetting(int settingID, String settingTitle, String settingValue, int status, String subjectName, String tyName) {
        this.settingID = settingID;
        this.settingTitle = settingTitle;
        this.settingValue = settingValue;
        this.status = status;
        this.subjectName = subjectName;
        this.tyName = tyName;
    }

    public SubjectSetting(int settingID, String settingTitle, String settingValue, int status, int subjectID, int typeID, String description) {
        this.settingID = settingID;
        this.settingTitle = settingTitle;
        this.settingValue = settingValue;
        this.status = status;
        this.subjectID = subjectID;
        this.typeID = typeID;
        this.description = description;
    }
    

    public int getSettingID() {
        return settingID;
    }

    public void setSettingID(int settingID) {
        this.settingID = settingID;
    }

    public String getSettingTitle() {
        return settingTitle;
    }

    public void setSettingTitle(String settingTitle) {
        this.settingTitle = settingTitle;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTyName() {
        return tyName;
    }

    public void setTyName(String tyName) {
        this.tyName = tyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
      
   
    

}
