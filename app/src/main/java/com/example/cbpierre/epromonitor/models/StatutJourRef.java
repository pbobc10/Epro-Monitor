package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "statut_jour_ref")
public class StatutJourRef {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "code")
    private String code;

    @Nullable
    @ColumnInfo(name = "nom")
    private String nom;

    @Nullable
    @ColumnInfo(name = "rang")
    private String rang;

    public StatutJourRef() {
    }

    public StatutJourRef(JSONObject jsonObject) {
        try {
            code = jsonNullRemoval(jsonObject.getString("CODE"));
            nom = jsonNullRemoval(jsonObject.getString("NOM"));
            rang = jsonNullRemoval(jsonObject.getString("RANG"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // remove null from the json String element
    public String jsonNullRemoval(String jsonElement) {
        return jsonElement.equals("null") ? null : jsonElement;
    }

    public static ArrayList<StatutJourRef> fromJSON(JSONArray jsonArray) {
        ArrayList<StatutJourRef> statutJourRefs = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                statutJourRefs.add(new StatutJourRef(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return statutJourRefs;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }

    @Nullable
    public String getNom() {
        return nom;
    }

    public void setNom(@Nullable String nom) {
        this.nom = nom;
    }

    @Nullable
    public String getRang() {
        return rang;
    }

    public void setRang(@Nullable String rang) {
        this.rang = rang;
    }
}
