package com.app.etouchcare.datamodel;

<<<<<<< HEAD
=======
import android.os.Parcel;
import android.os.Parcelable;

>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
/**
 * Created by wenzhongzheng on 2016-11-19.
 */

<<<<<<< HEAD
public class Patients {
=======
public class Patients implements Parcelable{
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454

    private String id="";
    private String pName="";
    private String diagnosis="";
    private String room="";
    private String diagnosisDetails="";
    private String condition="";

<<<<<<< HEAD
=======
    public Patients(){

    }
    public Patients(String id, String pName, String diagnosis, String room, String diagnosisDetails, String condition) {
        this.id = id;
        this.pName = pName;
        this.diagnosis = diagnosis;
        this.room = room;
        this.diagnosisDetails = diagnosisDetails;
        this.condition = condition;
    }

>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
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

<<<<<<< HEAD
=======

>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
    @Override
    public String toString() {
        return "id: "+id+", name: "+pName+", diagnosis: "+diagnosis+", room: "+room;
    }
<<<<<<< HEAD
=======

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.pName);
        dest.writeString(this.diagnosis);
        dest.writeString(this.room);
        dest.writeString(this.diagnosisDetails);
        dest.writeString(this.condition);
    }

    protected Patients(Parcel in) {
        this.id = in.readString();
        this.pName = in.readString();
        this.diagnosis = in.readString();
        this.room = in.readString();
        this.diagnosisDetails = in.readString();
        this.condition = in.readString();
    }

    public static final Creator<Patients> CREATOR = new Creator<Patients>() {
        @Override
        public Patients createFromParcel(Parcel source) {
            return new Patients(source);
        }

        @Override
        public Patients[] newArray(int size) {
            return new Patients[size];
        }
    };
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
}
