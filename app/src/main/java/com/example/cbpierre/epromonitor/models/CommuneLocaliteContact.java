package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "commune_localite_contact", primaryKeys = {"con_id","zone_ht_id"})
public class CommuneLocaliteContact {
    @NonNull
    @ColumnInfo(name = "con_id")
    private Integer conId;

    @NonNull
    @ColumnInfo(name = "zone_ht_id")
    private String zoneHtId;

    @NonNull
    @ColumnInfo(name = "commune")
    private String commune;

    @NonNull
    @ColumnInfo(name = "localite")
    private String localite;

    public CommuneLocaliteContact() {
    }

    public CommuneLocaliteContact(JSONObject jsonObject) {
        try {
            conId = jsonObject.getInt("CONID");
            zoneHtId = jsonNullRemoval(jsonObject.getString("ZONE_HT_ID"));
            commune = jsonNullRemoval(jsonObject.getString("COMMUNE"));
            localite = jsonNullRemoval(jsonObject.getString("LOCALITE"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<CommuneLocaliteContact> fromJSON(JSONArray jsonArray) {
        ArrayList<CommuneLocaliteContact> communeLocaliteContacts = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                communeLocaliteContacts.add(new CommuneLocaliteContact(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return communeLocaliteContacts;
    }

    // remove null from the json String element
    public String jsonNullRemoval(String jsonElement) {
        return jsonElement.equals("null") ? null : jsonElement;
    }

    @NonNull
    public Integer getConId() {
        return conId;
    }

    public void setConId(@NonNull Integer conId) {
        this.conId = conId;
    }

    @NonNull
    public String getZoneHtId() {
        return zoneHtId;
    }

    public void setZoneHtId(@NonNull String zoneHtId) {
        this.zoneHtId = zoneHtId;
    }

    @NonNull
    public String getCommune() {
        return commune;
    }

    public void setCommune(@NonNull String commune) {
        this.commune = commune;
    }

    @NonNull
    public String getLocalite() {
        return localite;
    }

    public void setLocalite(@NonNull String localite) {
        this.localite = localite;
    }
}
