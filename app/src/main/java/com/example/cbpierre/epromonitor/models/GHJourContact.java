package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@Entity(tableName = "gh_jour_contact", primaryKeys = {"gh_id", "jour", "con_id"})
public class GHJourContact {

    @Expose
    @SerializedName(value = "GHID")
    @NonNull
    @ColumnInfo(name = "gh_id")
    private Integer ghId;

    @Expose
    @SerializedName(value = "JOUR")
    @NonNull
    @ColumnInfo(name = "jour")
    private String jour;

    @Expose
    @SerializedName(value = "CONID")
    @NonNull
    @ColumnInfo(name = "con_id")
    private Integer conId;

    @Expose
    @SerializedName(value = "STATUT")
    @NonNull
    @ColumnInfo(name = "statut")
    private String statut;

    @Expose
    @SerializedName(value = "PROMOTION")
    @NonNull
    @ColumnInfo(name = "promotion")
    private Boolean promotion;

    @Expose
    @SerializedName(value = "LIVRAISON")
    @NonNull
    @ColumnInfo(name = "livraison")
    private Boolean livraison;

    @Expose
    @SerializedName(value = "RECOUVREMENT")
    @NonNull
    @ColumnInfo(name = "recouvrement")
    private Boolean recouvrement;

    @Expose
    @SerializedName(value = "AUTRE")
    @NonNull
    @ColumnInfo(name = "autre")
    private Boolean autre;

    @Expose
    @SerializedName(value = "DEBUT")
    @Nullable
    @ColumnInfo(name = "debut")
    private String debut;

    @Expose
    @SerializedName(value = "FIN")
    @Nullable
    @ColumnInfo(name = "fin")
    private String fin;

    @Expose
    @SerializedName(value = "LIEU")
    @Nullable
    @ColumnInfo(name = "lieu")
    private String lieu;

    @Expose
    @SerializedName(value = "AUTRE_LIEU")
    @Nullable
    @ColumnInfo(name = "autre_lieu")
    private String autreLieu;

    @Expose
    @SerializedName(value = "LATITUDE")
    @Nullable
    @ColumnInfo(name = "latitude")
    private String latitude;

    @Expose
    @SerializedName(value = "LONGITUDE")
    @Nullable
    @ColumnInfo(name = "longitude")
    private String longitude;

    @Expose
    @SerializedName(value = "NOTE")
    @Nullable
    @ColumnInfo(name = "note")
    private String note;

    @Expose
    @SerializedName(value = "CREE_PAR")
    @NonNull
    @ColumnInfo(name = "cree_par")
    private String creePar;

    @Expose
    @SerializedName(value = "CREE_LE")
    @NonNull
    @ColumnInfo(name = "cree_le")
    private String creele;

    @Expose
    @SerializedName(value = "MODIFIE_PAR")
    @NonNull
    @ColumnInfo(name = "modifie_par")
    private String modifiePar;

    @Expose
    @SerializedName(value = "MODIFIE_LE")
    @NonNull
    @ColumnInfo(name = "modifie_le")
    private String modifieLe;

    @Expose
    @SerializedName(value = "ANNULE")
    @NonNull
    @ColumnInfo(name = "annule")
    private Boolean annule;

    @Expose
    @SerializedName(value = "ANNULE_PAR")
    @Nullable
    @ColumnInfo(name = "annule_par")
    private String annulePar;

    @Expose
    @SerializedName(value = "ANNULE_LE")
    @Nullable
    @ColumnInfo(name = "annule_le")
    private String annuleLe;

    @Expose
    @SerializedName(value = "MOTIF_ANNULATION")
    @Nullable
    @ColumnInfo(name = "motif_annulation")
    private String motifAnnulation;

    //ajouter pour syncroniser les donnees
    //ne fait pas parti du model
    @Expose
    @SerializedName(value = "GH_JOUR_CONTACT_PRODUIT")
    @Ignore
    private List<GHJourContactProduit> ghJourContactProduitList;

    public GHJourContact() {
    }

    public GHJourContact(@NonNull Integer ghId, @NonNull String jour, @NonNull Integer conId, @NonNull String statut, @NonNull Boolean promotion, @NonNull Boolean livraison, @NonNull Boolean recouvrement, @NonNull Boolean autre, @Nullable String debut, @Nullable String fin, @Nullable String lieu, @Nullable String autreLieu, @Nullable String latitude, @Nullable String longitude, @Nullable String note, @NonNull String creePar, @NonNull String creele, @NonNull String modifiePar, @NonNull String modifieLe, @NonNull Boolean annule, @Nullable String annulePar, @Nullable String annuleLe, @Nullable String motifAnnulation) {
        this.ghId = ghId;
        this.jour = jour;
        this.conId = conId;
        this.statut = statut;
        this.promotion = promotion;
        this.livraison = livraison;
        this.recouvrement = recouvrement;
        this.autre = autre;
        this.debut = debut;
        this.fin = fin;
        this.lieu = lieu;
        this.autreLieu = autreLieu;
        this.latitude = latitude;
        this.longitude = longitude;
        this.note = note;
        this.creePar = creePar;
        this.creele = creele;
        this.modifiePar = modifiePar;
        this.modifieLe = modifieLe;
        this.annule = annule;
        this.annulePar = annulePar;
        this.annuleLe = annuleLe;
        this.motifAnnulation = motifAnnulation;
    }

    public GHJourContact(JSONObject jsonObject) {
        try {
            ghId = jsonObject.getInt("GHID");
            jour = jsonNullRemoval(jsonObject.getString("JOUR"));
            conId = jsonObject.getInt("CONID");
            statut = jsonNullRemoval(jsonObject.getString("STATUT"));
            promotion = jsonObject.getBoolean("PROMOTION");
            livraison = jsonObject.getBoolean("LIVRAISON");
            recouvrement = jsonObject.getBoolean("RECOUVREMENT");
            autre = jsonObject.getBoolean("AUTRE");
            debut = jsonNullRemoval(jsonObject.getString("DEBUT"));
            fin = jsonNullRemoval(jsonObject.getString("FIN"));
            lieu = jsonNullRemoval(jsonObject.getString("LIEU"));
            autreLieu = jsonNullRemoval(jsonObject.getString("AUTRE_LIEU"));
            latitude = jsonNullRemoval(jsonObject.getString("LATITUDE"));
            longitude = jsonNullRemoval(jsonObject.getString("LONGITUDE"));
            note = jsonNullRemoval(jsonObject.getString("NOTE"));
            creePar = jsonNullRemoval(jsonObject.getString("CREE_PAR"));
            creele = jsonNullRemoval(jsonObject.getString("CREE_LE"));
            modifiePar = jsonNullRemoval(jsonObject.getString("MODIFIE_PAR"));
            modifieLe = jsonNullRemoval(jsonObject.getString("MODIFIE_LE"));
            annule = jsonObject.getBoolean("ANNULE");
            annulePar = jsonNullRemoval(jsonObject.getString("ANNULE_PAR"));
            annuleLe = jsonNullRemoval(jsonObject.getString("ANNULE_LE"));
            motifAnnulation = jsonNullRemoval(jsonObject.getString("MOTIF_ANNULATION"));

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
    public String getStatut() {
        return statut;
    }

    public void setStatut(@NonNull String statut) {
        this.statut = statut;
    }

    @NonNull
    public Boolean getPromotion() {
        return promotion;
    }

    public void setPromotion(@NonNull Boolean promotion) {
        this.promotion = promotion;
    }

    @NonNull
    public Boolean getLivraison() {
        return livraison;
    }

    public void setLivraison(@NonNull Boolean livraison) {
        this.livraison = livraison;
    }

    @NonNull
    public Boolean getRecouvrement() {
        return recouvrement;
    }

    public void setRecouvrement(@NonNull Boolean recouvrement) {
        this.recouvrement = recouvrement;
    }

    @NonNull
    public Boolean getAutre() {
        return autre;
    }

    public void setAutre(@NonNull Boolean autre) {
        this.autre = autre;
    }

    @Nullable
    public String getDebut() {
        return debut;
    }

    public void setDebut(@Nullable String debut) {
        this.debut = debut;
    }

    @Nullable
    public String getFin() {
        return fin;
    }

    public void setFin(@Nullable String fin) {
        this.fin = fin;
    }

    @NonNull
    public String getLieu() {
        return lieu;
    }

    public void setLieu(@NonNull String lieu) {
        this.lieu = lieu;
    }

    @Nullable
    public String getAutreLieu() {
        return autreLieu;
    }

    public void setAutreLieu(@Nullable String autreLieu) {
        this.autreLieu = autreLieu;
    }

    @Nullable
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(@Nullable String latitude) {
        this.latitude = latitude;
    }

    @Nullable
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(@Nullable String longitude) {
        this.longitude = longitude;
    }

    @Nullable
    public String getNote() {
        return note;
    }

    public void setNote(@Nullable String note) {
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
    public String getCreele() {
        return creele;
    }

    public void setCreele(@NonNull String creele) {
        this.creele = creele;
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

    @NonNull
    public Boolean getAnnule() {
        return annule;
    }

    public void setAnnule(@NonNull Boolean annule) {
        this.annule = annule;
    }

    @Nullable
    public String getAnnulePar() {
        return annulePar;
    }

    public void setAnnulePar(@Nullable String annulePar) {
        this.annulePar = annulePar;
    }

    @Nullable
    public String getAnnuleLe() {
        return annuleLe;
    }

    public void setAnnuleLe(@Nullable String annuleLe) {
        this.annuleLe = annuleLe;
    }

    @Nullable
    public String getMotifAnnulation() {
        return motifAnnulation;
    }

    public void setMotifAnnulation(@Nullable String motifAnnulation) {
        this.motifAnnulation = motifAnnulation;
    }

    public List<GHJourContactProduit> getGhJourContactProduitList() {
        return ghJourContactProduitList;
    }

    public void setGhJourContactProduitList(List<GHJourContactProduit> ghJourContactProduitList) {
        this.ghJourContactProduitList = ghJourContactProduitList;
    }
}
