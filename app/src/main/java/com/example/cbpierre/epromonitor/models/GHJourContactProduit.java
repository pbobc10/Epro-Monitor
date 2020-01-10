package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "gh_jour_contact_produit", primaryKeys = {"gh_id", "jour", "con_id", "produit_id"},
        indices = {
                @Index(value = {"gh_id", "jour", "con_id"}, name = "ghJourContactProduit_index", unique = false),
                @Index(value = {"jour", "jour", "con_id", "produit_id"}, name = "ghJourContactProduit_produit_index", unique = false)
        }
)
public class GHJourContactProduit {
    @Expose
    @SerializedName("GHID")
    @NonNull
    @ColumnInfo(name = "gh_id")
    private Integer ghId;

    @Expose
    @SerializedName("JOUR")
    @NonNull
    @ColumnInfo(name = "jour")
    private String jour;

    @Expose
    @SerializedName("CONID")
    @NonNull
    @ColumnInfo(name = "con_id")
    private Integer conId;

    @Expose
    @SerializedName("PRODUIT_ID")
    @NonNull
    @ColumnInfo(name = "produit_id")
    private Integer produitId;

    @Expose
    @SerializedName("ACCEPT")
    @Nullable
    @ColumnInfo(name = "accept")
    private String accept;

    @Expose
    @SerializedName("NOTE")
    @Nullable
    @ColumnInfo(name = "note")
    private String note;

    public GHJourContactProduit() {
    }

    public GHJourContactProduit(@NonNull Integer ghId, @NonNull String jour, @NonNull Integer conId, @NonNull Integer produitId, @Nullable String accept, @Nullable String note) {
        this.ghId = ghId;
        this.jour = jour;
        this.conId = conId;
        this.produitId = produitId;
        this.accept = accept;
        this.note = note;
    }

    public GHJourContactProduit(JSONObject jsonObject) {
        try {
            ghId = jsonObject.getInt("GHID");
            jour = jsonNullRemoval(jsonObject.getString("JOUR"));
            conId = jsonObject.getInt("CONID");
            produitId = jsonObject.getInt("PRODUIT_ID");
            accept = jsonNullRemoval(jsonObject.getString("ACCEPT"));
            note = jsonNullRemoval(jsonObject.getString("NOTE"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    // remove null from the json String element
    public String jsonNullRemoval(String jsonElement) {
        return jsonElement.equals("null") ? null : jsonElement;
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
