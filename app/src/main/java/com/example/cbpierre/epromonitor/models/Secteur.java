package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "secteur_table")
public class Secteur {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "secId")
    private String secId;

    @NonNull
    @ColumnInfo(name = "nomSecteur")
    private String nomSecteur;

    @NonNull
    @ColumnInfo(name = "rang")
    private String rang;

    public Secteur() {
    }

    public Secteur(@NonNull String secId, @NonNull String nomSecteur, @NonNull String rang) {
        this.secId = secId;
        this.nomSecteur = nomSecteur;
        this.rang = rang;
    }

    public Secteur(JSONObject jsonObject) {
        try {
            this.secId = jsonObject.getString("SECID");
            this.nomSecteur = jsonObject.getString("NOM_SECTEUR");
            this.rang = jsonObject.getString("RANG");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //Factory method to convert an array of JSON objects into a list of objects
    public static ArrayList<Secteur> fromJson(JSONArray jsonArray) {
        ArrayList<Secteur> secteurs = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                secteurs.add(new Secteur(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return secteurs;
    }

    @NonNull
    public String getSecId() {
        return secId;
    }

    public void setSecId(@NonNull String secId) {
        this.secId = secId;
    }

    @NonNull
    public String getNomSecteur() {
        return nomSecteur;
    }

    public void setNomSecteur(@NonNull String nomSecteur) {
        this.nomSecteur = nomSecteur;
    }

    @NonNull
    public String getRang() {
        return rang;
    }

    public void setRang(@NonNull String rang) {
        this.rang = rang;
    }
}
