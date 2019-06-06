package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "specialite_table")
public class Specialite {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "spId")
    private String spId;

    @NonNull
    @ColumnInfo(name = "nomSpecialite")
    private String nomSpecialite;

    @NonNull
    @ColumnInfo(name = "rang")
    private String rang;

    @NonNull
    @ColumnInfo(name = "statut")
    private String statut;

    public Specialite() {
    }

    public Specialite(@NonNull String spId, @NonNull String nomSpecialite, @NonNull String rang, @NonNull String statut) {
        this.spId = spId;
        this.nomSpecialite = nomSpecialite;
        this.rang = rang;
        this.statut = statut;
    }

    public Specialite(JSONObject jsonObject) {
        try {
            this.spId = jsonObject.getString("SPID");
            this.nomSpecialite = jsonObject.getString("NOM_SPECIALITE");
            this.rang = jsonObject.getString("RANG");
            this.statut = jsonObject.getString("STATUT");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Specialite> fromJson(JSONArray jsonArray) {
        ArrayList<Specialite> specialites = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                specialites.add(new Specialite(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return specialites;
    }

    @NonNull
    public String getSpId() {
        return spId;
    }

    public void setSpId(@NonNull String spId) {
        this.spId = spId;
    }

    @NonNull
    public String getNomSpecialite() {
        return nomSpecialite;
    }

    public void setNomSpecialite(@NonNull String nomSpecialite) {
        this.nomSpecialite = nomSpecialite;
    }

    @NonNull
    public String getRang() {
        return rang;
    }

    public void setRang(@NonNull String rang) {
        this.rang = rang;
    }

    @NonNull
    public String getStatut() {
        return statut;
    }

    public void setStatut(@NonNull String statut) {
        this.statut = statut;
    }
}
