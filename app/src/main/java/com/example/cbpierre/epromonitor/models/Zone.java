package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "zone_table", indices = @Index(value = "zone_ht_id", name = "zone_index", unique = true))
public class Zone {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "zone_ht_id")
    private String zoneHtId;

    @NonNull
    @ColumnInfo(name = "description_zone_ht")
    private String descriptionZoneHt;

    @NonNull
    @ColumnInfo(name = "type_zone")
    private String typeZone;

    @NonNull
    @ColumnInfo(name = "departement")
    private String departement;

    @NonNull
    @ColumnInfo(name = "commune")
    private String commune;

    @NonNull
    @ColumnInfo(name = "localite")
    private String localite;

    public Zone() {
    }

    public Zone(@NonNull String zoneHtId, @NonNull String descriptionZoneHt, @NonNull String typeZone, @NonNull String departement, @NonNull String commune, @NonNull String localite) {
        this.zoneHtId = zoneHtId;
        this.descriptionZoneHt = descriptionZoneHt;
        this.typeZone = typeZone;
        this.departement = departement;
        this.commune = commune;
        this.localite = localite;
    }

    public Zone(JSONObject jsonObject) {
        try {
            this.zoneHtId = jsonObject.getString("ZONE_HT_ID");
            this.descriptionZoneHt = jsonObject.getString("DESCRIPTION_ZONE_HT");
            this.typeZone = jsonObject.getString("TYPE_ZONE");
            this.departement = jsonObject.getString("DEPARTEMENT");
            this.commune = jsonObject.getString("COMMUNE");
            this.localite = jsonObject.getString("LOCALITE").equals("null") ? null : jsonObject.getString("LOCALITE");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Zone> fromJson(JSONArray jsonArray) {
        ArrayList<Zone> zones = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                zones.add(new Zone(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return zones;
    }

    @NonNull
    public String getZoneHtId() {
        return zoneHtId;
    }

    public void setZoneHtId(@NonNull String zoneHtId) {
        this.zoneHtId = zoneHtId;
    }

    @NonNull
    public String getDescriptionZoneHt() {
        return descriptionZoneHt;
    }

    public void setDescriptionZoneHt(@NonNull String descriptionZoneHt) {
        this.descriptionZoneHt = descriptionZoneHt;
    }

    @NonNull
    public String getTypeZone() {
        return typeZone;
    }

    public void setTypeZone(@NonNull String typeZone) {
        this.typeZone = typeZone;
    }

    @NonNull
    public String getDepartement() {
        return departement;
    }

    public void setDepartement(@NonNull String departement) {
        this.departement = departement;
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
