package com.example.cbpierre.epromonitor;

public class AppConfig {

    /**
     * HTTP POST
     */

    // Server user login url
    public static final String URL_LOGIN = "https://disprophar.net/webAPI/Account/Post";
    // Server user register url
    public static final String URL_NEW_CONTACT_REGISTER = "https://disprophar.net/webAPI/Prospection/PostProspection";

    /**
     * HTTP GET
     */

    // Server titre url
    public static final String URL_TITRE = "https://disprophar.net/webAPI/Prospection/GetTitre";
    // Server nature url
    public static final String URL_NATURE = "https://disprophar.net/webAPI/Prospection/GetNature";
    // Server  secteur url
    public static final String URL_SECTEUR = "https://disprophar.net/webAPI/Prospection/GetSecteur";
    // Server  force url
    public static final String URL_FORCE = "https://disprophar.net/webAPI/Prospection/GetForce";
    // Server  specialite url
    public static final String URL_SPECIALITE = "https://disprophar.net/webAPI/Prospection/GetSpecialite";
    // Server  zone url
    public static final String URL_ZONE = "https://disprophar.net/webAPI/Prospection/GetZone";
    // Server  Contact url
    public static final String URL_CONTACT = "https://disprophar.net/webAPI/Prospection/GetContact?date=1/1/2018";
    //Server Etablissement url
    public static final String URL_ETABLISSEMENT = "https://disprophar.net/webAPI/Prospection/GetEtablissement?date=1/1/2018";

    /**
     * Plan d'Action PA
     **/

    //Server produit url
    public static final String URL_PRODUIT_REF = "https://disprophar.net/webAPI/Visite/GetProduit?cieId=";
    //Server PA, PA_CONTACT et PA_CONTACT_PRODUIT
    public static final String URL_PA = "https://disprophar.net/webAPI/Visite/GetPA?cieId=";
}