package com.example.cbpierre.epromonitor.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.NewContactETab;

@Dao
public interface NewContactEtabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewContactETab newContactETab);

    @Query("delete from new_contact_etab  where sync_data=0")
    void deleteNewContactEtabAfterSync();
}
