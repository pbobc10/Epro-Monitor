package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.GH;

import java.util.List;

@Dao
public interface GHDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGH(GH gh);

    @Query("delete from gh")
    void deleteGH();

    @Query("select * from gh order by gh.debut")
    LiveData<List<GH>> allGH();
}
