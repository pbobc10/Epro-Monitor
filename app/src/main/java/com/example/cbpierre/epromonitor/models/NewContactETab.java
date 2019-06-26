package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


@Entity(tableName = "new_contact_etab")
public class NewContactETab {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "contact_id")
    private Integer contactId;

    @Nullable
    @ColumnInfo(name = "etab_id")
    private Integer etabId;

    @Nullable
    @ColumnInfo(name = "new_etab_id")
    private Integer newEtabId;

    @NonNull
    @ColumnInfo(name = "sync_data")
    private Boolean syncData;

    public NewContactETab() {
    }

    public NewContactETab(@NonNull Integer contactId, @Nullable Integer etabId, @Nullable Integer newEtabId) {
        this.contactId = contactId;
        this.etabId = etabId;
        this.newEtabId = newEtabId;
        this.syncData = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(@NonNull Integer contactId) {
        this.contactId = contactId;
    }

    @Nullable
    public Integer getEtabId() {
        return etabId;
    }

    public void setEtabId(@Nullable Integer etabId) {
        this.etabId = etabId;
    }

    @Nullable
    public Integer getNewEtabId() {
        return newEtabId;
    }

    public void setNewEtabId(@Nullable Integer newEtabId) {
        this.newEtabId = newEtabId;
    }

    @NonNull
    public Boolean getSyncData() {
        return syncData;
    }

    public void setSyncData(@NonNull Boolean syncData) {
        this.syncData = syncData;
    }
}
