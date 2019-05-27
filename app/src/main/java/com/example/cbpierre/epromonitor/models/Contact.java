package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "contact_table",
        foreignKeys = {
                @ForeignKey(entity = Force.class, parentColumns = "fid", childColumns = "force"),
                @ForeignKey(entity = Nature.class, parentColumns = "natId", childColumns = "nature"),
                @ForeignKey(entity = Secteur.class, parentColumns = "secId", childColumns = "secteur"),
                @ForeignKey(entity = Specialite.class, parentColumns = "spId", childColumns = "specialite"),
                @ForeignKey(entity = Titre.class, parentColumns = "tid", childColumns = "titre")
        }
)
public class Contact implements Serializable {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "conId")
    private int conId;

    @Nullable
    @ColumnInfo(name = "titre")
    private String titre;

    @Nullable
    @ColumnInfo(name = "nom")
    private String nom;

    @Nullable
    @ColumnInfo(name = "prenom")
    private String prenom;

    @Nullable
    @ColumnInfo(name = "nature")
    private String nature;

    @Nullable
    @ColumnInfo(name = "secteur")
    private String secteur;

    @Nullable
    @ColumnInfo(name = "specialite")
    private String specialite;

    @Nullable
    @ColumnInfo(name = "force")
    private String force;

    @Nullable
    @ColumnInfo(name = "phone1")
    private String phone1;

    @Nullable
    @ColumnInfo(name = "phone2")
    private String phone2;

    @ColumnInfo(name = "phone3")
    private String phone3;

    @Nullable
    @ColumnInfo(name = "email")
    private String email;

    @Nullable
    @ColumnInfo(name = "statut")
    private int statut;

    @Nullable
    @ColumnInfo(name = "transfere_par")
    private String transfere_par;

    @Nullable
    @ColumnInfo(name = "transfere_le")
    private String transfere_le;

    @Nullable
    @ColumnInfo(name = "cree_par")
    private String cree_par;

    @Nullable
    @ColumnInfo(name = "cree_le")
    private String cree_le;

    @Nullable
    @ColumnInfo(name = "modifie_par")
    private String modifie_par;

    @Nullable
    @ColumnInfo(name = "modifie_le")
    private String modifie_le;

    @Nullable
    @ColumnInfo(name = "valide")
    private boolean valide;

    @Nullable
    @ColumnInfo(name = "validateur")
    private String validateur;

    @Nullable
    @ColumnInfo(name = "date_maj_valide")
    private String date_maj_valide;

    // constructor
    @Ignore
    public Contact() {
    }

    public Contact( /*int conId,*/ String titre, String nom, String prenom, String nature, String secteur, String specialite, String force, String phone1, String phone2, String phone3, String email, int statut, String transfere_par, String transfere_le, String cree_par, String cree_le, String modifie_par, String modifie_le, boolean valide, String validateur, String date_maj_valide) {
        //this.conId = conId;
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
        this.nature = nature;
        this.secteur = secteur;
        this.specialite = specialite;
        this.force = force;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.email = email;
        this.statut = statut;
        this.transfere_par = transfere_par;
        this.transfere_le = transfere_le;
        this.cree_par = cree_par;
        this.cree_le = cree_le;
        this.modifie_par = modifie_par;
        this.modifie_le = modifie_le;
        this.valide = valide;
        this.validateur = validateur;
        this.date_maj_valide = date_maj_valide;
    }


    public Contact(JSONObject jsonObject) {
        try {
            this.conId = jsonObject.getInt("CONID");
            this.titre = jsonObject.getString("TITRE").equals("null") ? null : jsonObject.getString("TITRE");
            this.nom = jsonObject.getString("NOM").equals("null") ? null : jsonObject.getString("NOM");
            this.prenom = jsonObject.getString("PRENOM").equals("null") ? null : jsonObject.getString("PRENOM");
            this.nature = jsonObject.getString("NATURE").equals("null") ? null : jsonObject.getString("NATURE");
            this.secteur = jsonObject.getString("SECTEUR").equals("null") ? null : jsonObject.getString("SECTEUR");
            this.specialite = jsonObject.getString("SPECIALITE").equals("null") ? null : jsonObject.getString("SPECIALITE");
            this.force = jsonObject.getString("FORCE");
            this.phone1 = jsonObject.getString("PHONE1").equals("null") ? null : jsonObject.getString("PHONE1");
            this.phone2 = jsonObject.getString("PHONE2").equals("null") ? null : jsonObject.getString("PHONE2");
            this.phone3 = jsonObject.getString("PHONE3").equals("null") ? null : jsonObject.getString("PHONE3");
            this.email = jsonObject.getString("EMAIL").equals("null") ? null : jsonObject.getString("EMAIL");
            this.statut = jsonObject.getInt("STATUT");
            this.transfere_par = jsonObject.getString("").equals("null") ? null : jsonObject.getString("");
            this.transfere_le = jsonObject.getString("").equals("null") ? null : jsonObject.getString("");
            this.cree_par = jsonObject.getString("").equals("null") ? null : jsonObject.getString("");
            this.cree_le = jsonObject.getString("").equals("null") ? null : jsonObject.getString("");
            this.modifie_par = jsonObject.getString("MODIFIE_PAR"); //== "null" ? null : jsonObject.getString("MODIFIE_PAR");
            this.modifie_le = jsonObject.getString("MODIFIE_LE");// == "null" ? null : jsonObject.getString("MODIFIE_LE");
            this.valide = jsonObject.getBoolean("VALIDE");
            this.validateur = jsonObject.getString("").equals("null") ? null : jsonObject.getString("");
            this.date_maj_valide = jsonObject.getString("").equals("null") ? null : jsonObject.getString("");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Contact> fromJson(JSONArray jsonArray) {
        ArrayList<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                contacts.add(new Contact(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return contacts;
    }

    public static ArrayList<ContactEtablissement> fromJsonEtabs(JSONArray jsonArray) {
        ArrayList<ContactEtablissement> etabs = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                int conn = jsonArray.getJSONObject(i).getInt("CONID");
                for (int j = 0; j < jsonArray.getJSONObject(i).getJSONArray("ETABS").length(); j++) {
                    etabs.add(new ContactEtablissement(conn, jsonArray.getJSONObject(i).getJSONArray("ETABS").getInt(j)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return etabs;
    }


    @NonNull
    public int getConId() {
        return conId;
    }

    public void setConId(@NonNull int conId) {
        this.conId = conId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getTransfere_par() {
        return transfere_par;
    }

    public void setTransfere_par(String transfere_par) {
        this.transfere_par = transfere_par;
    }

    public String getTransfere_le() {
        return transfere_le;
    }

    public void setTransfere_le(String transfere_le) {
        this.transfere_le = transfere_le;
    }

    public String getCree_par() {
        return cree_par;
    }

    public void setCree_par(String cree_par) {
        this.cree_par = cree_par;
    }

    public String getCree_le() {
        return cree_le;
    }

    public void setCree_le(String cree_le) {
        this.cree_le = cree_le;
    }

    public String getModifie_par() {
        return modifie_par;
    }

    public void setModifie_par(String modifie_par) {
        this.modifie_par = modifie_par;
    }

    public String getModifie_le() {
        return modifie_le;
    }

    public void setModifie_le(String modifie_le) {
        this.modifie_le = modifie_le;
    }

    public boolean getValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public String getValidateur() {
        return validateur;
    }

    public void setValidateur(String validateur) {
        this.validateur = validateur;
    }

    public String getDate_maj_valide() {
        return date_maj_valide;
    }

    public void setDate_maj_valide(String date_maj_valide) {
        this.date_maj_valide = date_maj_valide;
    }
}
