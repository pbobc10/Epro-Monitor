package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.CompleteEtablissement;
import com.example.cbpierre.epromonitor.models.Etablissement;

import java.util.List;

@Dao
public interface EtablissementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Etablissement etablissement);

    @Query("select * from etablissement_table order by nom_Etablissement")
    LiveData<List<Etablissement>> getAllEtablissement();

    @Query("select * from ETABLISSEMENT_TABLE,zone_table " +
            "where etablissement_table.localite=zone_table.zone_ht_id order by nom_Etablissement")
    LiveData<List<CompleteEtablissement>> getAllCompleteEtablissement();

    @Query("select * from ETABLISSEMENT_TABLE,zone_table " +
            "where etablissement_table.localite=zone_table.zone_ht_id " +
            "and etablissement_table.nom_Etablissement like '%' || :nom || '%'")
    LiveData<List<CompleteEtablissement>> getCompleteEtabsByNom(String nom);

    @Query("select * from etablissement_table where etablissement_table.localite like :localite ||'%' or nom_Etablissement = 'N''EST PAS DANS LA LISTE' order by nom_Etablissement")
    LiveData<List<Etablissement>> getEtabByLocalite(String localite);

}
