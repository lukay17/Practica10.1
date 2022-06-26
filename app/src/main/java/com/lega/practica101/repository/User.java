package com.lega.practica101.repository;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String User;
    private String surName;

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.User);
        dest.writeString(this.surName);
    }

    public void readFromParcel(Parcel source) {
        this.User = source.readString();
        this.surName = source.readString();
    }

    public User() {}

    protected User(Parcel in) {
        this.User = in.readString();
        this.surName = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
