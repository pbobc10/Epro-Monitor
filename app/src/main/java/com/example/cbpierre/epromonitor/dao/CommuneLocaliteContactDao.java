package com.example.cbpierre.epromonitor.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.CommuneLocaliteContact;

@Dao
public interface CommuneLocaliteContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCommuneLocaliteContact(CommuneLocaliteContact communeLocaliteContact);

    @Query("delete from commune_localite_contact")
    void deleteCommuneLocaliteContact();
}
