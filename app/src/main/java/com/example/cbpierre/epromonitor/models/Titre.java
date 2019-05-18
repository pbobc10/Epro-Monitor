package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "titre_table")
public class Titre {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tid")
    private String tid;

    @ColumnInfo(name = "nomTitre")
    private String nomTitre;

    @ColumnInfo(name = "rang")
    private String rang;


    public Titre() {
    }

    public Titre(JSONObject jsonObject) {
        try {
            this.tid = jsonObject.getString("TID");
            this.nomTitre = jsonObject.getString("TITRE");
            this.rang = jsonObject.getString("RANG");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //Factory method to convert an array of JSON objects into a list of objects

    public static ArrayList<Titre> fromJson(JSONArray jsonArray) {
        ArrayList<Titre> titres = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                titres.add(new Titre(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return titres;
    }

    @NonNull
    public String getTid() {
        return tid;
    }

    public void setTid(@NonNull String tid) {
        this.tid = tid;
    }

    public String getRang() {
        return rang;
    }

    public String getNomTitre() {
        return nomTitre;
    }

    public void setNomTitre(String nomTitre) {
        this.nomTitre = nomTitre;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }
}
