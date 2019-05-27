package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "etablissement_table")
public class Etablissement {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "etId")
    private String etId;

    @ColumnInfo(name = "nom_Etablissement")
    private String nomEtablissement;

    @ColumnInfo(name = "localite")
    private String localite;

    @ColumnInfo(name = "adresse")
    private String adresse;

    @ColumnInfo(name = "latitude")
    private String latitude;

    @ColumnInfo(name = "longitude")
    private String longitude;

    @ColumnInfo(name = "statut")
    private int statut;

    @ColumnInfo(name = "valide")
    private boolean valide;

    @ColumnInfo(name = "modifie_par")
    private String modifie_par;

    @ColumnInfo(name = "modifie_le")
    private String modifie_le;


    public Etablissement() {
    }

    public Etablissement(JSONObject jsonObject) {
        try {
            this.etId = jsonObject.getString("ETID");
            this.nomEtablissement = jsonObject.getString("NOM");
            this.localite = jsonObject.getString("LOCALITE");
            this.adresse = jsonObject.getString("ADRESSE");
            this.latitude = jsonObject.getString("LATITUDE");
            this.longitude = jsonObject.getString("LONGITUDE");
            this.statut = jsonObject.getInt("STATUT");
            this.valide = jsonObject.getBoolean("VALIDE");
            this.modifie_par = jsonObject.getString("MODIFIE_PAR");
            this.modifie_le = jsonObject.getString("MODIFIE_LE");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Etablissement> fromJson(JSONArray jsonArray) {
        ArrayList<Etablissement> etablissements = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                etablissements.add(new Etablissement(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return etablissements;
    }

    @NonNull
    public String getEtId() {
        return etId;
    }

    public String getNomEtablissement() {
        return nomEtablissement;
    }

    public String getLocalite() {
        return localite;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getStatut() {
        return statut;
    }

    public boolean isValide() {
        return valide;
    }

    public String getModifie_par() {
        return modifie_par;
    }

    public void setEtId(@NonNull String etId) {
        this.etId = etId;
    }

    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public void setModifie_par(String modifie_par) {
        this.modifie_par = modifie_par;
    }

    public void setModifie_le(String modifie_le) {
        this.modifie_le = modifie_le;
    }

    public String getModifie_le() {
        return modifie_le;
    }
}
