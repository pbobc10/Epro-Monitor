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

@Entity(tableName = "statut_visite_ref")
public class StatutVisiteRef {
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

    public StatutVisiteRef() {
    }

    public StatutVisiteRef(@NonNull String code, @Nullable String nom, @Nullable String rang) {
        this.code = code;
        this.nom = nom;
        this.rang = rang;
    }

    public StatutVisiteRef(JSONObject jsonObject) {
        try {
            code = jsonNullRemoval(jsonObject.getString("CODE"));
            nom = jsonNullRemoval(jsonObject.getString("NOM"));
            rang = jsonNullRemoval(jsonObject.getString("RANG"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<StatutVisiteRef> fromJSON(JSONArray jsonArray) {
        ArrayList<StatutVisiteRef> statutVisiteRefs = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                statutVisiteRefs.add(new StatutVisiteRef(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return statutVisiteRefs;
    }

    // remove null from the json String element
    public String jsonNullRemoval(String jsonElement) {
        return jsonElement.equals("null") ? null : jsonElement;
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
