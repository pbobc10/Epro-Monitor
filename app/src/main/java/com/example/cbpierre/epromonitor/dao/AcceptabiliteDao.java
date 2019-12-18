package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.AcceptabiliteRef;

import java.util.List;

@Dao
public interface AcceptabiliteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAcceptabilite(AcceptabiliteRef acceptabiliteRef);

    @Query("delete from acceptabilite_ref")
    void delteAcceptabilite();

    @Query("select * from acceptabilite_ref order by acceptabilite_ref.nom")
    LiveData<List<AcceptabiliteRef>> getAllAcceptabiliteRef();
}
