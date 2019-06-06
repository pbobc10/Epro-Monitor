package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "nature_table")
public class Nature {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "natId")
    private String natId;


    @NonNull
    @ColumnInfo(name = "nomNature")
    private String nomNature;


    @NonNull
    @ColumnInfo(name = "rang")
    private String rang;


    public Nature() {
    }


    public Nature(@NonNull String natId, @NonNull String nomNature, @NonNull String rang) {
        this.natId = natId;
        this.nomNature = nomNature;
        this.rang = rang;
    }

    public Nature(JSONObject jsonObject) {
        try {
            this.natId = jsonObject.getString("NATID");
            this.nomNature = jsonObject.getString("NOM_NATURE");
            this.rang = jsonObject.getString("RANG");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //Factory method to convert an array of JSON objects into a list of objects
    public static ArrayList<Nature> fromJson(JSONArray jsonArray) {
        ArrayList<Nature> natures = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                natures.add(new Nature(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return natures;
    }

    @NonNull
    public String getNatId() {
        return natId;
    }

    public void setNatId(@NonNull String natId) {
        this.natId = natId;
    }

    @NonNull
    public String getNomNature() {
        return nomNature;
    }

    public void setNomNature(@NonNull String nomNature) {
        this.nomNature = nomNature;
    }

    @NonNull
    public String getRang() {
        return rang;
    }

    public void setRang(@NonNull String rang) {
        this.rang = rang;
    }
}
