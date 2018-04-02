package com.test.khrushch.testapplication.structure;

import android.os.Parcel;
import android.os.Parcelable;

public class CityInfo implements Parcelable {

    private int id;
    private String name;

    public CityInfo() {
    }

    public CityInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    protected CityInfo(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<CityInfo> CREATOR = new Parcelable.Creator<CityInfo>() {
        @Override
        public CityInfo createFromParcel(Parcel source) {
            return new CityInfo(source);
        }

        @Override
        public CityInfo[] newArray(int size) {
            return new CityInfo[size];
        }
    };
}
