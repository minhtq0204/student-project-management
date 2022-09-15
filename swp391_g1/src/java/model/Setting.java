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
public class Setting {

    private int settingID;
    private String settingValue;
    private String settingNote;
    private int status;
    private int typeId;
    private String typeName;

    public Setting() {
    }

    
    public Setting(int settingID, String settingValue, String settingNote, int status, int typeId, String typeName) {
        this.settingID = settingID;
        this.settingValue = settingValue;
        this.settingNote = settingNote;
        this.status = status;
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public Setting(int settingID, String settingValue, String settingNote, int status, int typeId) {
        this.settingID = settingID;
        this.settingValue = settingValue;
        this.settingNote = settingNote;
        this.status = status;
        this.typeId = typeId;
    }

    public Setting(String settingValue, String settingNote, int status, int typeId) {
        this.settingValue = settingValue;
        this.settingNote = settingNote;
        this.status = status;
        this.typeId = typeId;
    }

    public Setting(String settingValue, String settingNote, int status, String typeName) {
        this.settingValue = settingValue;
        this.settingNote = settingNote;
        this.status = status;
        this.typeName = typeName;
    }
    
    

    public int getSettingID() {
        return settingID;
    }

    public void setSettingID(int settingID) {
        this.settingID = settingID;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public String getSettingNote() {
        return settingNote;
    }

    public void setSettingNote(String settingNote) {
        this.settingNote = settingNote;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Setting{" + "settingID=" + settingID + ", settingValue=" + settingValue + ", settingNote=" + settingNote + ", status=" + status + ", typeId=" + typeId + ", typeName=" + typeName + '}';
    }
    
    

}
