package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "gh")
public class GH {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "gh_id")
    private Integer ghId;

    @NonNull
    @ColumnInfo(name = "pa_id")
    private Integer paId;

    @NonNull
    @ColumnInfo(name = "debut")
    private String debut;

    @NonNull
    @ColumnInfo(name = "fin")
    private String fin;

    @NonNull
    @ColumnInfo(name = "cree_par")
    private String creePar;

    @NonNull
    @ColumnInfo(name = "cree_le")
    private String creePle;

    @NonNull
    @ColumnInfo(name = "modifie_par")
    private String modifiePar;

    @NonNull
    @ColumnInfo(name = "midifie_le")
    private String modifieLe;

    @Nullable
    @ColumnInfo(name = "transfere_par")
    private String transferePar;

    @Nullable
    @ColumnInfo(name = "transfere_le")
    private String transfereLe;

    @NonNull
    @ColumnInfo(name = "gh_complete")
    private Boolean ghComplete;

    @Nullable
    @ColumnInfo(name = "complete_par")
    private String completePar;

    @Nullable
    @ColumnInfo(name = "complete_le")
    private String completeLe;

    @NonNull
    @ColumnInfo(name = "row_version")
    private String rowVersion;

    @NonNull
    @ColumnInfo(name = "intro_version")
    private Integer introwVersion;

    protected GH(JSONObject jsonObject) {
        try {
            ghId = jsonObject.getInt("GHID");
            paId = jsonObject.getInt("PAID");
            debut = jsonObject.getString("DEBUT");
            fin = jsonObject.getString("FIN");
            creePar = jsonObject.getString("CREE_PAR");
            creePle = jsonObject.getString("CREE_LE");
            modifiePar = jsonObject.getString("MODIFIE_PAR");
            modifieLe = jsonObject.getString("MODIFIE_LE");
            transferePar = jsonObject.getString("TRANSFERE_PAR");
            transfereLe = jsonObject.getString("TRANSFERE_LE");
            ghComplete = jsonObject.getBoolean("GH_COMPLETE");
            completePar = jsonObject.getString("COMPLETE_PAR");
            completeLe = jsonObject.getString("COMPLETE_LE");
            rowVersion = jsonObject.getString("ROW_VERSION");
            introwVersion = jsonObject.getInt("INTROWVERSION");
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
