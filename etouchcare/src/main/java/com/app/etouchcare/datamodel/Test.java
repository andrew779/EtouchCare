package com.app.etouchcare.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wenzhongzheng on 2016-12-13.
 */

public class Test implements Parcelable{

    String _id,patientId,testId,name,result,date;

    public Test() {
    }

    public Test(String _id, String patientId, String testId, String name, String result, String date) {
        this._id = _id;
        this.patientId = patientId;
        this.testId = testId;
        this.name = name;
        this.result = result;
        this.date = date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.patientId);
        dest.writeString(this.testId);
        dest.writeString(this.name);
        dest.writeString(this.result);
        dest.writeString(this.date);
    }

    protected Test(Parcel in) {
        this._id = in.readString();
        this.patientId = in.readString();
        this.testId = in.readString();
        this.name = in.readString();
        this.result = in.readString();
        this.date = in.readString();
    }

    public static final Creator<Test> CREATOR = new Creator<Test>() {
        @Override
        public Test createFromParcel(Parcel source) {
            return new Test(source);
        }

        @Override
        public Test[] newArray(int size) {
            return new Test[size];
        }
    };
}
