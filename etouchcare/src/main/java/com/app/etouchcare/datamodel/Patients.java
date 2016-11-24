package com.app.etouchcare.datamodel;

<<<<<<< HEAD
=======
import android.os.Parcel;
import android.os.Parcelable;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;

=======
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
>>>>>>> b4dece0f13f164ec703afb21d910522b9c1665ec
/**
 * Created by wenzhongzheng on 2016-11-19.
 */

<<<<<<< HEAD
public class Patients implements Parcelable {
=======
<<<<<<< HEAD
public class Patients {
=======
public class Patients implements Parcelable{
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
>>>>>>> b4dece0f13f164ec703afb21d910522b9c1665ec

    private String id = "";
    private String pName = "";
    private String diagnosis = "";
    private String room = "";
    private String diagnosisDetails = "";
    private String condition = "";
    private String email = "";
    private String address = "";
    private String phone = "";
    private String emergencyName = "";
    private String emergencyPhone = "";

<<<<<<< HEAD
    public Patients() {
=======
<<<<<<< HEAD
=======
    public Patients(){
>>>>>>> b4dece0f13f164ec703afb21d910522b9c1665ec

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
        return "id: " + id + ", name: " + pName + ", diagnosis: " + diagnosis + ", room: " + room;
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
<<<<<<< HEAD

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }
=======
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
>>>>>>> b4dece0f13f164ec703afb21d910522b9c1665ec
}
