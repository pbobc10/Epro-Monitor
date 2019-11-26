package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "gh")
public class GH {
    @Expose
    @SerializedName(value = "GHID")
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "gh_id")
    private Integer ghId;

    @Expose
    @SerializedName(value = "PAID")
    @NonNull
    @ColumnInfo(name = "pa_id")
    private Integer paId;

    @Expose
    @SerializedName(value = "DEBUT")
    @NonNull
    @ColumnInfo(name = "debut")
    private String debut;

    @Expose
    @SerializedName(value = "FIN")
    @NonNull
    @ColumnInfo(name = "fin")
    private String fin;

    @Expose
    @NonNull
    @ColumnInfo(name = "statut_temporel")
    private String statutTemporel;

    @Expose
    @SerializedName(value = "CREE_PAR")
    @NonNull
    @ColumnInfo(name = "cree_par")
    private String creePar;

    @Expose
    @SerializedName(value = "CREE_LE")
    @NonNull
    @ColumnInfo(name = "cree_le")
    private String creePle;

    @Expose
    @SerializedName(value = "MODIFIE_PAR")
    @NonNull
    @ColumnInfo(name = "modifie_par")
    private String modifiePar;

    @Expose
    @SerializedName(value = "MODIFIE_LE")
    @NonNull
    @ColumnInfo(name = "midifie_le")
    private String modifieLe;

    @Expose
    @SerializedName(value = "TRANSFERE_PAR")
    @Nullable
    @ColumnInfo(name = "transfere_par")
    private String transferePar;

    @Expose
    @SerializedName(value = "TRANSFERE_LE")
    @Nullable
    @ColumnInfo(name = "transfere_le")
    private String transfereLe;

    @Expose
    @SerializedName(value = "GH_COMPLETE")
    @NonNull
    @ColumnInfo(name = "gh_complete")
    private Boolean ghComplete;

    @Expose
    @SerializedName(value = "COMPLETE_PAR")
    @Nullable
    @ColumnInfo(name = "complete_par")
    private String completePar;

    @Expose
    @SerializedName(value = "COMPLETE_LE")
    @Nullable
    @ColumnInfo(name = "complete_le")
    private String completeLe;

    @Expose
    @SerializedName(value = "ROW_VERSION")
    @NonNull
    @ColumnInfo(name = "row_version")
    private String rowVersion;

    @Expose
    @SerializedName(value = "INTROWVERSION")
    @NonNull
    @ColumnInfo(name = "intro_version")
    private Integer introwVersion;

    public GH() {
    }

    protected GH(JSONObject jsonObject) {
        try {
            ghId = jsonObject.getInt("GHID");
            paId = jsonObject.getInt("PAID");
            debut = jsonNullRemoval(jsonObject.getString("DEBUT"));
            fin = jsonNullRemoval(jsonObject.getString("FIN"));
            statutTemporel = jsonNullRemoval(jsonObject.getString("STATUT_TEMPOREL"));
            creePar = jsonNullRemoval(jsonObject.getString("CREE_PAR"));
            creePle = jsonNullRemoval(jsonObject.getString("CREE_LE"));
            modifiePar = jsonNullRemoval(jsonObject.getString("MODIFIE_PAR"));
            modifieLe = jsonNullRemoval(jsonObject.getString("MODIFIE_LE"));
            transferePar = jsonNullRemoval(jsonObject.getString("TRANSFERE_PAR"));
            transfereLe = jsonNullRemoval(jsonObject.getString("TRANSFERE_LE"));
            ghComplete = jsonObject.getBoolean("GH_COMPLETE");
            completePar = jsonNullRemoval(jsonObject.getString("COMPLETE_PAR"));
            completeLe = jsonNullRemoval(jsonObject.getString("COMPLETE_LE"));
            rowVersion = jsonNullRemoval(jsonObject.getString("ROW_VERSION"));
            introwVersion = jsonObject.getInt("INTROWVERSION");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<GH> fromJSONGH(JSONArray jsonArray) {
        ArrayList<GH> ghs = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                ghs.add(new GH(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ghs;
    }

    public static ArrayList<GHJour> fromJSONGhJour(JSONArray jsonArray) {
        ArrayList<GHJour> ghJours = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                for (int j = 0; j < jsonArray.getJSONObject(i).getJSONArray("GH_JOUR").length(); j++) {
                    ghJours.add(new GHJour(jsonArray.getJSONObject(i).getJSONArray("GH_JOUR").getJSONObject(j)));
                }
            } catch (JSONException e) {
            }

        }
        return ghJours;
    }

    public static ArrayList<GHJourContact> fromJSONGHJourContact(JSONArray jsonArray) throws JSONException {
        ArrayList<GHJourContact> ghJourContacts = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            for (int j = 0; j < jsonArray.getJSONObject(i).getJSONArray("GH_JOUR").length(); j++) {
                for (int k = 0; k < jsonArray.getJSONObject(i).getJSONArray("GH_JOUR").getJSONObject(j).getJSONArray("GH_JOUR_CONTACT").length(); k++) {
                    ghJourContacts.add(new GHJourContact(jsonArray.getJSONObject(i).getJSONArray("GH_JOUR").getJSONObject(j).getJSONArray("GH_JOUR_CONTACT").getJSONObject(k)));
                }
            }

        }
        return ghJourContacts;
    }

    public static ArrayList<GHJourContactProduit> fromJSONGHJourContactProduit(JSONArray jsonArray) throws JSONException {
        ArrayList<GHJourContactProduit> ghJourContactProduits = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            for (int j = 0; j < jsonArray.getJSONObject(i).getJSONArray("GH_JOUR").length(); j++) {
                for (int k = 0; k < jsonArray.getJSONObject(i).getJSONArray("GH_JOUR").getJSONObject(j).getJSONArray("GH_JOUR_CONTACT").length(); k++) {
                    for (int l = 0; l < jsonArray.getJSONObject(i).getJSONArray("GH_JOUR").getJSONObject(j).getJSONArray("GH_JOUR_CONTACT").getJSONObject(k).getJSONArray("GH_JOUR_CONTACT_PRODUIT").length(); l++) {
                        ghJourContactProduits.add(new GHJourContactProduit(jsonArray.getJSONObject(i).getJSONArray("GH_JOUR").getJSONObject(j).getJSONArray("GH_JOUR_CONTACT").getJSONObject(k).getJSONArray("GH_JOUR_CONTACT_PRODUIT").getJSONObject(l)));
                    }
                }
            }

        }
        return ghJourContactProduits;
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
    public Integer getPaId() {
        return paId;
    }

    public void setPaId(@NonNull Integer paId) {
        this.paId = paId;
    }

    @NonNull
    public String getDebut() {
        return debut;
    }

    public void setDebut(@NonNull String debut) {
        this.debut = debut;
    }

    @NonNull
    public String getFin() {
        return fin;
    }

    public void setFin(@NonNull String fin) {
        this.fin = fin;
    }

    @NonNull
    public String getStatutTemporel() {
        return statutTemporel;
    }

    public void setStatutTemporel(@NonNull String statutTemporel) {
        this.statutTemporel = statutTemporel;
    }

    @NonNull
    public String getCreePar() {
        return creePar;
    }

    public void setCreePar(@NonNull String creePar) {
        this.creePar = creePar;
    }

    @NonNull
    public String getCreePle() {
        return creePle;
    }

    public void setCreePle(@NonNull String creePle) {
        this.creePle = creePle;
    }

    @NonNull
    public String getModifiePar() {
        return modifiePar;
    }

    public void setModifiePar(@NonNull String modifiePar) {
        this.modifiePar = modifiePar;
    }

    @NonNull
    public String getModifieLe() {
        return modifieLe;
    }

    public void setModifieLe(@NonNull String modifieLe) {
        this.modifieLe = modifieLe;
    }

    @Nullable
    public String getTransferePar() {
        return transferePar;
    }

    public void setTransferePar(@Nullable String transferePar) {
        this.transferePar = transferePar;
    }

    @Nullable
    public String getTransfereLe() {
        return transfereLe;
    }

    public void setTransfereLe(@Nullable String transfereLe) {
        this.transfereLe = transfereLe;
    }

    @NonNull
    public Boolean getGhComplete() {
        return ghComplete;
    }

    public void setGhComplete(@NonNull Boolean ghComplete) {
        this.ghComplete = ghComplete;
    }

    @Nullable
    public String getCompletePar() {
        return completePar;
    }

    public void setCompletePar(@Nullable String completePar) {
        this.completePar = completePar;
    }

    @Nullable
    public String getCompleteLe() {
        return completeLe;
    }

    public void setCompleteLe(@Nullable String completeLe) {
        this.completeLe = completeLe;
    }

    @NonNull
    public String getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(@NonNull String rowVersion) {
        this.rowVersion = rowVersion;
    }

    @NonNull
    public Integer getIntrowVersion() {
        return introwVersion;
    }

    public void setIntrowVersion(@NonNull Integer introwVersion) {
        this.introwVersion = introwVersion;
    }
}
