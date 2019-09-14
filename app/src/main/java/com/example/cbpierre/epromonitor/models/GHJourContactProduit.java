package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "gh_jour_contact_produit", primaryKeys = {"gh_id", "jour", "con_id", "produit_id"})
public class GHJourContactProduit {
    @NonNull
    @ColumnInfo(name = "gh_id")
    private Integer ghId;

    @NonNull
    @ColumnInfo(name = "jour")
    private String jour;

    @NonNull
    @ColumnInfo(name = "con_id")
    private Integer conId;

    @NonNull
    @ColumnInfo(name = "produit_id")
    private Integer produitId;

    @Nullable
    @ColumnInfo(name = "accept")
    private String accept;

    @Nullable
    @ColumnInfo(name = "note")
    private String note;

    public GHJourContactProduit(JSONObject jsonObject) {
        try {
            ghId = jsonObject.getInt("GHID");
            jour = jsonObject.getString("JOUR");
            conId = jsonObject.getInt("CONID");
            produitId = jsonObject.getInt("PRODUIT_ID");
            accept = jsonObject.getString("ACCEPT");
            note = jsonObject.getString("NOTE");
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
    public Integer getConId() {
        return conId;
    }

    public void setConId(@NonNull Integer conId) {
        this.conId = conId;
    }

    @NonNull
    public Integer getProduitId() {
        return produitId;
    }

    public void setProduitId(@NonNull Integer produitId) {
        this.produitId = produitId;
    }

    @Nullable
    public String getAccept() {
        return accept;
    }

    public void setAccept(@Nullable String accept) {
        this.accept = accept;
    }

    @Nullable
    public String getNote() {
        return note;
    }

    public void setNote(@Nullable String note) {
        this.note = note;
    }
}
