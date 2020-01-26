package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "pa_contact_produit", primaryKeys = {"pa_id", "con_id", "produit_id"}, indices = { @Index(value = {"pa_id", "con_id", "produit_id"}, name = "pa_contact_produit_index2", unique = true)})
public class PaContactProduit {
    @NonNull
    @ColumnInfo(name = "pa_id")
    private Integer paId;

    @NonNull
    @ColumnInfo(name = "con_id")
    private Integer conId;

    @NonNull
    @ColumnInfo(name = "produit_id")
    private Integer produitId;

    public PaContactProduit() {
    }

    public PaContactProduit(@NonNull Integer paId, @NonNull Integer conId, @NonNull Integer produitId) {
        this.paId = paId;
        this.conId = conId;
        this.produitId = produitId;
    }

    public PaContactProduit(JSONObject jsonObject) {
        try {
            paId = jsonObject.getInt("PAID");
            conId = jsonObject.getInt("CONID");
            produitId = jsonObject.getInt("PRODUIT_ID");
        } catch (JSONException e) {
        }
    }

    @NonNull
    public Integer getPaId() {
        return paId;
    }

    public void setPaId(@NonNull Integer paId) {
        this.paId = paId;
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
}
