package com.example.cbpierre.epromonitor.models;

public class CompleteEtablissement {
    //Eblissement
    private String etId;
    private String nom_Etablissement;
    private String localite;
    private String adresse;
    private String latitude;
    private String longitude;
    private int statut;
    private boolean valide;
    private String modifie_par;
    private String modifie_le;

    //Zone
    private String zoneHtId;
    private String description_zone_ht;
    private String typeZone;
    private String departement;
    private String commune;
    private String localiteZone;

    public CompleteEtablissement(String etId, String nom_Etablissement, String localite, String adresse, String latitude, String longitude, int statut, boolean valide, String modifie_par, String modifie_le, String zoneHtId, String description_zone_ht, String typeZone, String departement, String commune, String localiteZone) {
        this.etId = etId;
        this.nom_Etablissement = nom_Etablissement;
        this.localite = localite;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.statut = statut;
        this.valide = valide;
        this.modifie_par = modifie_par;
        this.modifie_le = modifie_le;
        this.zoneHtId = zoneHtId;
        this.description_zone_ht = description_zone_ht;
        this.typeZone = typeZone;
        this.departement = departement;
        this.commune = commune;
        this.localiteZone = localiteZone;
    }

    public String getEtId() {
        return etId;
    }

    public String getNom_Etablissement() {
        return nom_Etablissement;
    }

    public String getLocalite() {
        return localite;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getStatut() {
        return statut;
    }

    public boolean isValide() {
        return valide;
    }

    public String getModifie_par() {
        return modifie_par;
    }

    public String getModifie_le() {
        return modifie_le;
    }

    public String getZoneHtId() {
        return zoneHtId;
    }

    public String getDescription_zone_ht() {
        return description_zone_ht;
    }

    public String getTypeZone() {
        return typeZone;
    }

    public String getDepartement() {
        return departement;
    }

    public String getCommune() {
        return commune;
    }

    public String getLocaliteZone() {
        return localiteZone;
    }
}
