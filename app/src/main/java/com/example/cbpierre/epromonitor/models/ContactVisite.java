package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "contact_visite",
        indices = @Index(value = "con_id", name = "contact_visite_index", unique = true))
public class ContactVisite {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contact_visite_id")
    private Integer conVisteIs;

    @NonNull
    @ColumnInfo(name = "con_id")
    private Integer conId;

    @NonNull
    @ColumnInfo(name = "nom_ratio")
    private String nomRatio;

    @NonNull
    @ColumnInfo(name = "specialite")
    private String specialite;

    public ContactVisite() {
    }

    public ContactVisite(JSONObject jsonObject) {
        try {
            conId = jsonObject.getInt("CONID");
            nomRatio = jsonObject.getString("NOM_RATIO");
            specialite = jsonObject.getString("SPECIALITE");
        } catch (JSONException e) {
        }
    }

    public static ArrayList<ContactVisite> fromJson(JSONArray jsonArray) {
        ArrayList<ContactVisite> contactVisites = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                contactVisites.add(new ContactVisite(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return contactVisites;
    }

    @NonNull
    public Integer getConVisteIs() {
        return conVisteIs;
    }

    public void setConVisteIs(@NonNull Integer conVisteIs) {
        this.conVisteIs = conVisteIs;
    }

    @NonNull
    public Integer getConId() {
        return conId;
    }

    public void setConId(@NonNull Integer conId) {
        this.conId = conId;
    }

    @NonNull
    public String getNomRatio() {
        return nomRatio;
    }

    public void setNomRatio(@NonNull String nomRatio) {
        this.nomRatio = nomRatio;
    }

    @NonNull
    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(@NonNull String specialite) {
        this.specialite = specialite;
    }
}
