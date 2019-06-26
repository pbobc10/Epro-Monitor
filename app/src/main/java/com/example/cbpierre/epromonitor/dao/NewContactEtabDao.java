package com.example.cbpierre.epromonitor.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.example.cbpierre.epromonitor.models.NewContactETab;

@Dao
public interface NewContactEtabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewContactETab newContactETab);
}
