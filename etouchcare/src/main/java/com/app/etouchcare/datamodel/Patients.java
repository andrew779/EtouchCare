/**
 * TeamOne
 */
package com.app.etouchcare.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by wenzhongzheng on 2016-11-19.
 */



public class Patients implements Parcelable, Serializable {

    private String id = "";
    private String pname = "";
    private String diagnosis = "";
    private String room = "";
    private String diagnosisDetails = "";
    private String condition = "";
    private String email = "";
    private String address = "";
    private String phone = "";
    private String age = "";
    private String emergencyName = "";
    private String emergencyPhone = "";

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender = "";



        public Patients(){


    }

    //unit test
    public Patients(String id, String name, String diagnosis, String room, String diagnosisDetails, String condition) {
        this.id = id;
        this.pname = name;
        this.diagnosis = diagnosis;
        this.room = room;
        this.diagnosisDetails = diagnosisDetails;
        this.condition = condition;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return pname;
    }

    public void setName(String name) {
        this.pname = name;
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
        return "id: " + id + ", name: " + pname + ", diagnosis: " + diagnosis + ", room: " + room
                +", age: "+age
                +", email: "+email
                +", phone: "+phone
                +", address: "+address
                +", em_name: "+emergencyName
                +", em_phone: "+emergencyPhone
                ;
    }


    public String getEmail() {
        return email;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.pname);
        dest.writeString(this.diagnosis);
        dest.writeString(this.room);
        dest.writeString(this.diagnosisDetails);
        dest.writeString(this.condition);
        dest.writeString(this.email);
        dest.writeString(this.address);
        dest.writeString(this.phone);
        dest.writeString(this.age);
        dest.writeString(this.emergencyName);
        dest.writeString(this.emergencyPhone);
        dest.writeString(this.gender);
    }

    protected Patients(Parcel in) {
        this.id = in.readString();
        this.pname = in.readString();
        this.diagnosis = in.readString();
        this.room = in.readString();
        this.diagnosisDetails = in.readString();
        this.condition = in.readString();
        this.email = in.readString();
        this.address = in.readString();
        this.phone = in.readString();
        this.age = in.readString();
        this.emergencyName = in.readString();
        this.emergencyPhone = in.readString();
        this.gender = in.readString();
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
}
