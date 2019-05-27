package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

@Entity(tableName = "contact_etablissement",
        primaryKeys={"conIds","etIds"}
       /* foreignKeys = {
                @ForeignKey(entity = Contact.class, parentColumns = "conId", childColumns = "conIds"),
                @ForeignKey(entity = Etablissement.class, parentColumns = "etId", childColumns = "etIds")
        }*/
)
public class ContactEtablissement {

   // @PrimaryKey(autoGenerate = true)
   // private String ceId;

    @Nullable
    @ColumnInfo(name = "conIds")
    private int conIds;

    @Nullable
    @ColumnInfo(name = "etIds")
    private int etIds;

    public ContactEtablissement(int conIds, int etIds) {
        this.conIds = conIds;
        this.etIds = etIds;
    }

    public int getConIds() {
        return conIds;
    }

    public void setConIds(int conIds) {
        this.conIds = conIds;
    }

    public int getEtIds() {
        return etIds;
    }

    public void setEtIds(int etIds) {
        this.etIds = etIds;
    }
}
