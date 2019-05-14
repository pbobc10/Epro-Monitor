package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.Zone;

import java.util.List;

@Dao
public interface ZoneDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Zone zone);

    @Query("select * from zone_table order by description_zone_ht asc")
    LiveData<List<Zone>> getAllZone();
}
