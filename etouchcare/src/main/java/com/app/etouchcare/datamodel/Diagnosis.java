package com.app.etouchcare.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wenzhongzheng on 2016-12-13.
 */

public class Diagnosis implements Parcelable{
    String _id,patientId,description,date;

    public Diagnosis() {
    }

    public Diagnosis(String _id, String patientId, String description, String date) {
        this._id = _id;
        this.patientId = patientId;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        dest.writeString(this.description);
        dest.writeString(this.date);
    }

    protected Diagnosis(Parcel in) {
        this._id = in.readString();
        this.patientId = in.readString();
        this.description = in.readString();
        this.date = in.readString();
    }

    public static final Creator<Diagnosis> CREATOR = new Creator<Diagnosis>() {
        @Override
        public Diagnosis createFromParcel(Parcel source) {
            return new Diagnosis(source);
        }

        @Override
        public Diagnosis[] newArray(int size) {
            return new Diagnosis[size];
        }
    };
}
