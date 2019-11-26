package com.example.cbpierre.epromonitor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SendGH {
    @Expose
    @SerializedName(value = "GHID")
    private Integer ghId;

    @Expose
    @SerializedName(value = "PAID")
    private Integer paId;

    @Expose
    @SerializedName(value = "DEBUT")
    private String debut;

    @Expose
    @SerializedName(value = "FIN")
    private String fin;

    @Expose
    @SerializedName(value = "CREE_PAR")
    private String creePar;

    @Expose
    @SerializedName(value = "CREE_LE")
    private String creePle;

    @Expose
    @SerializedName(value = "MODIFIE_PAR")
    private String modifiePar;

    @Expose
    @SerializedName(value = "MODIFIE_LE")
    private String modifieLe;

    @Expose
    @SerializedName(value = "TRANSFERE_PAR")
    private String transferePar;

    @Expose
    @SerializedName(value = "TRANSFERE_LE")
    private String transfereLe;

    @Expose
    @SerializedName(value = "GH_COMPLETE")
    private Boolean ghComplete;

    @Expose
    @SerializedName(value = "COMPLETE_PAR")
    private String completePar;

    @Expose
    @SerializedName(value = "COMPLETE_LE")
    private String completeLe;

    @Expose
    @SerializedName(value = "ROW_VERSION")
    private String rowVersion;

    @Expose
    @SerializedName(value = "INTROWVERSION")
    private Integer introwVersion;

    @Expose
    @SerializedName(value = "GH_JOUR")
    private List<GHJour> ghJourList;
   /* @SerializedName(value = "GH_JOUR_CONTACT")
    private List<GHJourContact> ghJourContactList;*/

    public SendGH() {
    }

    public Integer getGhId() {
        return ghId;
    }

    public void setGhId(Integer ghId) {
        this.ghId = ghId;
    }

    public Integer getPaId() {
        return paId;
    }

    public void setPaId(Integer paId) {
        this.paId = paId;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getCreePar() {
        return creePar;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public String getCreePle() {
        return creePle;
    }

    public void setCreePle(String creePle) {
        this.creePle = creePle;
    }

    public String getModifiePar() {
        return modifiePar;
    }

    public void setModifiePar(String modifiePar) {
        this.modifiePar = modifiePar;
    }

    public String getModifieLe() {
        return modifieLe;
    }

    public void setModifieLe(String modifieLe) {
        this.modifieLe = modifieLe;
    }

    public String getTransferePar() {
        return transferePar;
    }

    public void setTransferePar(String transferePar) {
        this.transferePar = transferePar;
    }

    public String getTransfereLe() {
        return transfereLe;
    }

    public void setTransfereLe(String transfereLe) {
        this.transfereLe = transfereLe;
    }

    public Boolean getGhComplete() {
        return ghComplete;
    }

    public void setGhComplete(Boolean ghComplete) {
        this.ghComplete = ghComplete;
    }

    public String getCompletePar() {
        return completePar;
    }

    public void setCompletePar(String completePar) {
        this.completePar = completePar;
    }

    public String getCompleteLe() {
        return completeLe;
    }

    public void setCompleteLe(String completeLe) {
        this.completeLe = completeLe;
    }

    public String getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(String rowVersion) {
        this.rowVersion = rowVersion;
    }

    public Integer getIntrowVersion() {
        return introwVersion;
    }

    public void setIntrowVersion(Integer introwVersion) {
        this.introwVersion = introwVersion;
    }

    public List<GHJour> getGhJourList() {
        return ghJourList;
    }

    public void setGhJourList(List<GHJour> ghJourList) {
        this.ghJourList = ghJourList;
    }

}
