package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "gh_jour_contact", primaryKeys = {"gh_id", "jour", "con_id"})
public class GHJourContact {
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
    @ColumnInfo(name = "statut")
    private String statut;

    @NonNull
    @ColumnInfo(name = "promotion")
    private Boolean promotion;

    @NonNull
    @ColumnInfo(name = "livraison")
    private Boolean livraison;

    @NonNull
    @ColumnInfo(name = "recouvrement")
    private Boolean recouvrement;

    @NonNull
    @ColumnInfo(name = "autre")
    private Boolean autre;

    @Nullable
    @ColumnInfo(name = "debut")
    private String debut;

    @Nullable
    @ColumnInfo(name = "fin")
    private String fin;

    @Nullable
    @ColumnInfo(name = "lieu")
    private String lieu;

    @Nullable
    @ColumnInfo(name = "autre_lieu")
    private String autreLieu;

    @Nullable
    @ColumnInfo(name = "latitude")
    private String latitude;

    @Nullable
    @ColumnInfo(name = "longitude")
    private String longitude;

    @Nullable
    @ColumnInfo(name = "note")
    private String note;

    @NonNull
    @ColumnInfo(name = "cree_par")
    private String creePar;

    @NonNull
    @ColumnInfo(name = "cree_le")
    private String creele;

    @NonNull
    @ColumnInfo(name = "modifie_par")
    private String modifiePar;

    @NonNull
    @ColumnInfo(name = "modifie_le")
    private String modifieLe;

    @NonNull
    @ColumnInfo(name = "annule")
    private Boolean annule;

    @Nullable
    @ColumnInfo(name = "annule_par")
    private String annulePar;

    @Nullable
    @ColumnInfo(name = "annule_le")
    private String annuleLe;

    @Nullable
    @ColumnInfo(name = "motif_annulation")
    private String motifAnnulation;

    public GHJourContact() {
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
}
