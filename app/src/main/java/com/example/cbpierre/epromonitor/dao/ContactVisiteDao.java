package com.example.cbpierre.epromonitor.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.example.cbpierre.epromonitor.models.ContactVisite;

@Dao
public interface ContactVisiteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContactVisite(ContactVisite contactVisite);

    @Delete
    void deleteContatVisite(ContactVisite contactVisite);

}
