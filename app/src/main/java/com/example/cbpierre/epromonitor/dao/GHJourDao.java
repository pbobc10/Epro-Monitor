package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.GHJour;
import com.example.cbpierre.epromonitor.models.JoinGHJourStatutRef;

import java.util.List;

@Dao
public interface GHJourDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGHJour(GHJour ghJour);

    @Query("delete from gh_jour")
    void deleteGHJour();

    @Query("select gh_jour.gh_id,gh_jour.jour,statut_jour_ref.nom,gh_jour.cree_par,gh_jour.cree_le,gh_jour.modifie_le,gh_jour.modifie_par\n" +
            ",gh_jour.transfere_le,gh_jour.transfere_par,gh_jour.rapport_complete,gh_jour.complete_le,gh_jour.complete_par,gh.gh_complete from gh_jour,statut_jour_ref,gh\n" +
            "where gh_jour.gh_id=:ghId\n" +
            "and gh_jour.statut=statut_jour_ref.code\n" +
            "and gh_jour.gh_id=gh.gh_id\n" +
            "order by gh_jour.jour")
    LiveData<List<JoinGHJourStatutRef>> allJour(Integer ghId);

    @Query("select * from gh_jour order by gh_id,jour")
    List<GHJour> ghJourList();
}
