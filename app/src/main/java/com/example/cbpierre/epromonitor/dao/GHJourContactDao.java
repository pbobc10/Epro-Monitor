package com.example.cbpierre.epromonitor.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.GHJourContact;

@Dao
public interface GHJourContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGHJOurContact(GHJourContact ghJourContact);

    @Query("delete from gh_jour_contact")
    void deleteGHJourContact();
}
