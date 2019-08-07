package com.example.cbpierre.epromonitor.models;

public class JoinContactEtablissementData {
    private String nom_Etablissement;
    private String adresse;
    private String description_zone_ht;
    private Boolean is_new_etab;
    private Integer etabId;
    private Integer etId;
    private Boolean modified;


    public JoinContactEtablissementData(Integer etabId, Integer etId, String nom_Etablissement, String adresse, String description_zone_ht, Boolean is_new_etab, Boolean modified) {
        this.nom_Etablissement = nom_Etablissement;
        this.adresse = adresse;
        this.description_zone_ht = description_zone_ht;
        this.is_new_etab = is_new_etab;
        this.etabId = etabId;
        this.etId = etId;
        this.modified = modified;
    }

    public String getNom_Etablissement() {
        return nom_Etablissement;
    }

    public void setNom_Etablissement(String nom_Etablissement) {
        this.nom_Etablissement = nom_Etablissement;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription_zone_ht() {
        return description_zone_ht;
    }

    public void setDescription_zone_ht(String description_zone_ht) {
        this.description_zone_ht = description_zone_ht;
    }

    public Boolean isIs_new_etab() {
        return is_new_etab;
    }

    public void setIs_new_etab(Boolean is_new_etab) {
        this.is_new_etab = is_new_etab;
    }

    public Integer getEtabId() {
        return etabId;
    }

    public void setEtabId(Integer etabId) {
        this.etabId = etabId;
    }

    public Integer getEtId() {
        return etId;
    }

    public void setEtId(Integer etId) {
        this.etId = etId;
    }

    public Boolean getModified() {
        return modified;
    }

    public void setModified(Boolean modified) {
        this.modified = modified;
    }
}
