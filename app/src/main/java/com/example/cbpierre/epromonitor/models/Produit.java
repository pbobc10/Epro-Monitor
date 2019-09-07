package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "produit")
public class Produit {
    @NonNull
    @ColumnInfo(name = "produit_id")
    @PrimaryKey(autoGenerate = false)
    private Integer produitId;

    @NonNull
    @ColumnInfo(name = "nom_produit")
    private String nomProduit;

    public Produit() {
    }

    public Produit(JSONObject jsonObject) {
        try {
            produitId = jsonObject.getInt("PRODUIT_ID");
            nomProduit = jsonObject.getString("NOM_PRODUIT");
        } catch (JSONException e) {
        }
    }

    public static ArrayList<Produit> fromJson(JSONArray jsonArray) {
        ArrayList<Produit> produits = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                produits.add(new Produit(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
            }
        }
        return produits;
    }

    @NonNull
    public Integer getProduitId() {
        return produitId;
    }

    public void setProduitId(@NonNull Integer produitId) {
        this.produitId = produitId;
    }

    @NonNull
    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(@NonNull String nomProduit) {
        this.nomProduit = nomProduit;
    }
}
