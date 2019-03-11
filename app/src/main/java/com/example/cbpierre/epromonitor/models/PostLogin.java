package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "postLogin_table")
public class PostLogin {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "cieId")
    private String cieId;

    @NonNull
    @ColumnInfo(name = "nomCie")
    private String nomCie;

    @NonNull
    @ColumnInfo(name = "typePromo")
    private String typePromo;

    @NonNull
    @ColumnInfo(name = "userId")
    private String userId;

    @NonNull
    @ColumnInfo(name = "mobId")
    private String mobId;

    @NonNull
    @ColumnInfo(name = "cleMob")
    private String cleMob;

    @NonNull
    @ColumnInfo(name = "msg")
    private String msg;

    public PostLogin() {
    }

    public PostLogin(JSONObject jsonObject) {
        try {
            this.cieId = jsonObject.getString("cieId");
            this.nomCie = jsonObject.getString("nomCie");
            this.typePromo = jsonObject.getString("typePromo");
            this.userId = jsonObject.getString("userId");
            this.mobId = jsonObject.getString("mobId");
            this.cleMob = jsonObject.getString("cleMob");
            this.msg = jsonObject.getString("msg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @NonNull
    public String getCieId() {
        return cieId;
    }

    public void setCieId(@NonNull String cieId) {
        this.cieId = cieId;
    }

    @NonNull
    public String getNomCie() {
        return nomCie;
    }

    public void setNomCie(@NonNull String nomCie) {
        this.nomCie = nomCie;
    }

    @NonNull
    public String getTypePromo() {
        return typePromo;
    }

    public void setTypePromo(@NonNull String typePromo) {
        this.typePromo = typePromo;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @NonNull
    public String getMobId() {
        return mobId;
    }

    public void setMobId(@NonNull String mobId) {
        this.mobId = mobId;
    }

    @NonNull
    public String getCleMob() {
        return cleMob;
    }

    public void setCleMob(@NonNull String cleMob) {
        this.cleMob = cleMob;
    }

    @NonNull
    public String getMsg() {
        return msg;
    }

    public void setMsg(@NonNull String msg) {
        this.msg = msg;
    }
}
