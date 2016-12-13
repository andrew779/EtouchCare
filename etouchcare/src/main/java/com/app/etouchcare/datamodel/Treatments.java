/**
 * TeamOne
 */
package com.app.etouchcare.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wenzhongzheng on 2016-11-25.
 */

public class Treatments implements Parcelable {
    private String _id,description,date,patientId;

    public Treatments() {
    }

    public Treatments(String _id, String description, String date, String patientId) {

        this._id = _id;
        this.description = description;
        this.date = date;
        this.patientId = patientId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "id: "+_id+", Description: "+description+", PatientId: "+patientId+", date: "+date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeString(this.patientId);
    }

    protected Treatments(Parcel in) {
        this._id = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.patientId = in.readString();
    }

    public static final Parcelable.Creator<Treatments> CREATOR = new Parcelable.Creator<Treatments>() {
        @Override
        public Treatments createFromParcel(Parcel source) {
            return new Treatments(source);
        }

        @Override
        public Treatments[] newArray(int size) {
            return new Treatments[size];
        }
    };
}
