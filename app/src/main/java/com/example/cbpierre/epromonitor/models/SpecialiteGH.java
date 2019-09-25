package com.example.cbpierre.epromonitor.models;

public class SpecialiteGH {
    private String nomSpecialite;
    private String spId;

    public SpecialiteGH() {
    }

    public SpecialiteGH(String nomSpecialite, String spId) {
        this.nomSpecialite = nomSpecialite;
        this.spId = spId;
    }

    public String getNomSpecialite() {
        return nomSpecialite;
    }

    public void setNomSpecialite(String nomSpecialite) {
        this.nomSpecialite = nomSpecialite;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }
}
