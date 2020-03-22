package com.example.cbpierre.epromonitor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendRecommandation {
    @Expose
    @SerializedName(value = "GHID")
    private int ghId;
    @Expose
    @SerializedName(value = "JOUR")
    private String jour;
    @Expose
    @SerializedName(value = "CIEID")
    private String cieId;

    public SendRecommandation() {
    }

    public SendRecommandation(int ghId, String jour, String cieId) {
        this.ghId = ghId;
        this.jour = jour;
        this.cieId = cieId;
    }

    public int getGhId() {
        return ghId;
    }

    public void setGhId(int ghId) {
        this.ghId = ghId;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getCieId() {
        return cieId;
    }

    public void setCieId(String cieId) {
        this.cieId = cieId;
    }
}
