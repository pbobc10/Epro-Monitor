package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "force_table")
public class Force {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "fid")
    private String fid;

    @NonNull
    @ColumnInfo(name = "nomForce")
    private String nomForce;

    public Force() {
    }

    public Force(JSONObject jsonObject) {
        try {
            this.fid = jsonObject.getString("FID");
            this.nomForce = jsonObject.getString("NOM_FORCE");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Force> fromJson(JSONArray jsonArray) {
        ArrayList<Force> forces = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                forces.add(new Force(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return forces;
    }


    @NonNull
    public String getFid() {
        return fid;
    }

    public void setFid(@NonNull String fid) {
        this.fid = fid;
    }

    @NonNull
    public String getNomForce() {
        return nomForce;
    }

    public void setNomForce(@NonNull String nomForce) {
        this.nomForce = nomForce;
    }
}