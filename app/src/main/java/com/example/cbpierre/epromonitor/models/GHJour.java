package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "gh_jour", primaryKeys = {"gh_id", "jour"})
public class GHJour {
    @NonNull
    @ColumnInfo(name = "gh_id")
    private Integer ghId;

    @NonNull
    @ColumnInfo(name = "jour")
    private String jour;

    @NonNull
    @ColumnInfo(name = "statut")
    private String statut;

    @NonNull
    @ColumnInfo(name = "cree_par")
    private String creePar;

    @NonNull
    @ColumnInfo(name = "cree_le")
    private String creeLe;

    @NonNull
    @ColumnInfo(name = "modifie_par")
    private String modifiePar;

    @NonNull
    @ColumnInfo(name = "modifie_le")
    private String modifieLe;

    @Nullable
    @ColumnInfo(name = "transfere_par")
    private String transferePar;

    @Nullable
    @ColumnInfo(name = "transfere_le")
    private String transfereLe;

    @NonNull
    @ColumnInfo(name = "rapport_complete")
    private Boolean rapportComplete;

    @Nullable
    @ColumnInfo(name = "complete_par")
    private String completePar;

    @Nullable
    @ColumnInfo(name = "complete_le")
    private String completeLe;

    @Nullable
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

    @Nullable
    @ColumnInfo(name = "row_version")
    private String rowVersion;

    @NonNull
    @ColumnInfo(name = "int_row_version")
    private Integer intRowVersion;

    public GHJour() {
    }

    public GHJour(JSONObject jsonObject) {
        try {
            ghId = jsonObject.getInt("GHID");
            jour = jsonNullRemoval(jsonObject.getString("JOUR"));
            statut = jsonNullRemoval(jsonObject.getString("STATUT"));
            creePar = jsonNullRemoval(jsonObject.getString("CREE_PAR"));
            creeLe = jsonNullRemoval(jsonObject.getString("CREE_LE"));
            modifiePar = jsonNullRemoval(jsonObject.getString("MODIFIE_PAR"));
            modifieLe = jsonNullRemoval(jsonObject.getString("MODIFIE_LE"));
            transferePar = jsonNullRemoval(jsonObject.getString("TRANSFERE_PAR"));
            transfereLe = jsonNullRemoval(jsonObject.getString("TRANSFERE_LE"));
            rapportComplete = jsonObject.getBoolean("RAPPORT_COMPLETE");
            completePar = jsonNullRemoval(jsonObject.getString("COMPLETE_PAR"));
            completeLe = jsonNullRemoval(jsonObject.getString("COMPLETE_LE"));
            annule = jsonObject.getBoolean("ANNULE");
            annulePar = jsonNullRemoval(jsonObject.getString("ANNULE_PAR"));
            annuleLe = jsonNullRemoval(jsonObject.getString("ANNULE_LE"));
            motifAnnulation = jsonNullRemoval(jsonObject.getString("MOTIF_ANNULATION"));
            rowVersion = jsonNullRemoval(jsonObject.getString("ROW_VERSION"));
            intRowVersion = jsonObject.getInt("INTROWVERSION");
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
    public String getStatut() {
        return statut;
    }

    public void setStatut(@NonNull String statut) {
        this.statut = statut;
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
    public String getTransferePar() {
        return transferePar;
    }

    public void setTransferePar(@NonNull String transferePar) {
        this.transferePar = transferePar;
    }

    @NonNull
    public String getTransfereLe() {
        return transfereLe;
    }

    public void setTransfereLe(@NonNull String transfereLe) {
        this.transfereLe = transfereLe;
    }

    @NonNull
    public Boolean getRapportComplete() {
        return rapportComplete;
    }

    public void setRapportComplete(@NonNull Boolean rapportComplete) {
        this.rapportComplete = rapportComplete;
    }

    @NonNull
    public String getCompletePar() {
        return completePar;
    }

    public void setCompletePar(@NonNull String completePar) {
        this.completePar = completePar;
    }

    @NonNull
    public String getCompleteLe() {
        return completeLe;
    }

    public void setCompleteLe(@NonNull String completeLe) {
        this.completeLe = completeLe;
    }

    @NonNull
    public Boolean getAnnule() {
        return annule;
    }

    public void setAnnule(@NonNull Boolean annule) {
        this.annule = annule;
    }

    @NonNull
    public String getAnnulePar() {
        return annulePar;
    }

    public void setAnnulePar(@NonNull String annulePar) {
        this.annulePar = annulePar;
    }

    @NonNull
    public String getAnnuleLe() {
        return annuleLe;
    }

    public void setAnnuleLe(@NonNull String annuleLe) {
        this.annuleLe = annuleLe;
    }

    @NonNull
    public String getMotifAnnulation() {
        return motifAnnulation;
    }

    public void setMotifAnnulation(@NonNull String motifAnnulation) {
        this.motifAnnulation = motifAnnulation;
    }

    @NonNull
    public String getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(@NonNull String rowVersion) {
        this.rowVersion = rowVersion;
    }

    @NonNull
    public Integer getIntRowVersion() {
        return intRowVersion;
    }

    public void setIntRowVersion(@NonNull Integer intRowVersion) {
        this.intRowVersion = intRowVersion;
    }
}
