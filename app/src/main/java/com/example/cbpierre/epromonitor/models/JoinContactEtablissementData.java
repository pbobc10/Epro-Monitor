package com.example.cbpierre.epromonitor.models;

public class JoinContactEtablissementData {
    private String nom_Etablissement;
    private String adresse;
    private String description_zone_ht;

    public JoinContactEtablissementData(String nom_Etablissement, String adresse, String description_zone_ht) {
        this.nom_Etablissement = nom_Etablissement;
        this.adresse = adresse;
        this.description_zone_ht = description_zone_ht;
    }

    public String getNom_Etablissement() {
        return nom_Etablissement;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getDescription_zone_ht() {
        return description_zone_ht;
    }
}
