package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "pa_contact", primaryKeys = {"pa_id", "con_id"}
        , indices = {@Index(value = {"pa_id", "con_id"}, name = "pa_contact_index1", unique = false)
        , @Index(value = "con_id", name = "pa_contact_index2", unique = false)
}
)
public class PaContact {
    @NonNull
    @ColumnInfo(name = "pa_id")
    private Integer paId;

    @NonNull
    @ColumnInfo(name = "con_id")
    private Integer conId;

    @NonNull
    @ColumnInfo(name = "force")
    private String force;

    @NonNull
    @ColumnInfo(name = "quota")
    private String quota;

    public PaContact() {
    }

    public PaContact(JSONObject jsonObject) {
        try {
            paId = jsonObject.getInt("PAID");
            conId = jsonObject.getInt("CONID");
            force = jsonObject.getString("FORCE");
            quota = jsonObject.getString("QUOTA");
        } catch (JSONException e) {
        }
    }

    @NonNull
    public Integer getPaId() {
        return paId;
    }

    public void setPaId(@NonNull Integer paId) {
        this.paId = paId;
    }

    @NonNull
    public Integer getConId() {
        return conId;
    }

    public void setConId(@NonNull Integer conId) {
        this.conId = conId;
    }

    @NonNull
    public String getForce() {
        return force;
    }

    public void setForce(@NonNull String force) {
        this.force = force;
    }

    @NonNull
    public String getQuota() {
        return quota;
    }

    public void setQuota(@NonNull String quota) {
        this.quota = quota;
    }
}
