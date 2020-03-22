package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "recommandation", primaryKeys = {"gh_id", "jour"})
public class Recommandation {
    @NonNull
    @ColumnInfo(name = "gh_id")
    private Integer ghId;
    @NonNull
    @ColumnInfo(name = "jour")
    private String jour;
    @NonNull
    @ColumnInfo(name = "note")
    private String note;
    @NonNull
    @ColumnInfo(name = "cree_par")
    private String creePar;
    @NonNull
    @ColumnInfo(name = "cree_le")
    private String creeLe;
    @Nullable
    @ColumnInfo(name = "modifie_par")
    private String modifiePar;
    @Nullable
    @ColumnInfo(name = "modifie_le")
    private String modifieLe;
    @Nullable
    @ColumnInfo(name = "telecharge")
    private Boolean telecharge;
    @Nullable
    @ColumnInfo(name = "telecharge_par")
    private String telechargePar;
    @Nullable
    @ColumnInfo(name = "telecharge_le")
    private String telechargeLe;

    public Recommandation() {
    }

    public Recommandation(JSONObject jsonObject) {
        try {
            ghId = jsonObject.getInt("GHID");
            this.jour = jsonObject.getString("JOUR");
            this.note = jsonObject.getString("NOTE");
            this.creePar = jsonObject.getString("CREE_PAR");
            this.creeLe = jsonObject.getString("CREE_LE");
            this.modifiePar = jsonObject.getString("MODIFIE_PAR");
            this.modifieLe = jsonObject.getString("MODIFIE_LE");
            this.telecharge = jsonObject.getBoolean("TELECHARGE");
            this.telechargePar = jsonObject.getString("TELECHARGE_PAR");
            this.telechargeLe = jsonObject.getString("TELECHARGE_LE");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Recommandation> fromJsonArray(JSONArray jsonArray) {
        ArrayList<Recommandation> recommandations = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                recommandations.add(new Recommandation(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return recommandations;
    }

    @NonNull
    public Integer getGhId() {
        return ghId;
    }

    public void setGhId(@NonNull Integer ghId) {
        this.ghId = ghId;
    }

    @NonNull
    public String getJour() {
        return jour;
    }

    public void setJour(@NonNull String jour) {
        this.jour = jour;
    }

    @NonNull
    public String getNote() {
        return note;
    }

    public void setNote(@NonNull String note) {
        this.note = note;
    }

    @NonNull
    public String getCreePar() {
        return creePar;
    }

    public void setCreePar(@NonNull String creePar) {
        this.creePar = creePar;
    }

    @NonNull
    public String getCreeLe() {
        return creeLe;
    }

    public void setCreeLe(@NonNull String creeLe) {
        this.creeLe = creeLe;
    }

    @Nullable
    public String getModifiePar() {
        return modifiePar;
    }

    public void setModifiePar(@Nullable String modifiePar) {
        this.modifiePar = modifiePar;
    }

    @Nullable
    public String getModifieLe() {
        return modifieLe;
    }

    public void setModifieLe(@Nullable String modifieLe) {
        this.modifieLe = modifieLe;
    }

    @Nullable
    public Boolean getTelecharge() {
        return telecharge;
    }

    public void setTelecharge(@Nullable Boolean telecharge) {
        this.telecharge = telecharge;
    }

    @Nullable
    public String getTelechargePar() {
        return telechargePar;
    }

    public void setTelechargePar(@Nullable String telechargePar) {
        this.telechargePar = telechargePar;
    }

    @Nullable
    public String getTelechargeLe() {
        return telechargeLe;
    }

    public void setTelechargeLe(@Nullable String telechargeLe) {
        this.telechargeLe = telechargeLe;
    }
}
