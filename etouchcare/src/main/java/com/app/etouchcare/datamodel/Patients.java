package com.app.etouchcare.datamodel;

/**
 * Created by wenzhongzheng on 2016-11-19.
 */

public class Patients {

    private String id="";
    private String pName="";
    private String diagnosis="";
    private String room="";
    private String diagnosisDetails="";
    private String condition="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDiagnosisDetails() {
        return diagnosisDetails;
    }

    public void setDiagnosisDetails(String diagnosisDetails) {
        this.diagnosisDetails = diagnosisDetails;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "id: "+id+", name: "+pName+", diagnosis: "+diagnosis+", room: "+room;
    }
}
