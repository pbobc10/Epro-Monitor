package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

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

    public StatutVisiteRef(JSONObject jsonObject) {
        try {
            code = jsonObject.getString("CODE");
            nom = jsonObject.getString("NOM");
            rang = jsonObject.getString("RANG");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
