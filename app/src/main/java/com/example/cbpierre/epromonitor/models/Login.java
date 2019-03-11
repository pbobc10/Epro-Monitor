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

    @NonNull
    @ColumnInfo(name = "codeMob")
    private String mCodeMob;

    @NonNull
    @ColumnInfo(name = "imei1")
    private String mImei1;

    @ColumnInfo(name = "imei2")
    private String mImei2;

    public Login(String username, String password, String codeMob, String imei1, String imei2) {
        this.mUsername = username;
        this.mPassword = password;
        this.mCodeMob = codeMob;
        this.mImei1 = imei1;
        this.mImei2 = imei2;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(@NonNull String username) {
        this.mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(@NonNull String password) {
        this.mPassword = password;
    }


    public String getCodeMob() {
        return mCodeMob;
    }

    public void setCodeMob(@NonNull String mCodeMob) {
        this.mCodeMob = mCodeMob;
    }


    public String getImei1() {
        return mImei1;
    }

    public void setImei1(@NonNull String mImei1) {
        this.mImei1 = mImei1;
    }

    public String getImei2() {
        return mImei2;
    }

    public void setImei2(String mImei2) {
        this.mImei2 = mImei2;
    }
}
