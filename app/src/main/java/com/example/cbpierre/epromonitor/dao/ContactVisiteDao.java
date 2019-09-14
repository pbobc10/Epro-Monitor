package com.example.cbpierre.epromonitor.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.ContactVisite;

@Dao
public interface ContactVisiteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContactVisite(ContactVisite contactVisite);

    @Query("delete from contact_visite")
    void deleteContatVisite();

}
