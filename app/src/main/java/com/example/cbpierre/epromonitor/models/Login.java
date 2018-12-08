package com.example.cbpierre.epromonitor.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "login_table")
public class Login {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    private String mUsername;

    @NonNull
    @ColumnInfo(name = "password")
    private String mPassword;

    public Login(String username, String password) {
        this.mUsername = username;
        this.mPassword = password;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setUsername(@NonNull String username) {
        this.mUsername = username;
    }

    public void setPassword(@NonNull String password) {
        this.mPassword = password;
    }
}
