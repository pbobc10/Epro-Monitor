package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.CompleteEtablissement;
import com.example.cbpierre.epromonitor.models.Etablissement;
import com.example.cbpierre.epromonitor.models.JoinNewEtabNewContact;
import com.example.cbpierre.epromonitor.models.OldEtablissement;

import java.util.List;

@Dao
public interface EtablissementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Etablissement etablissement);

    @Query("select * from etablissement_table order by nom_Etablissement")
    LiveData<List<Etablissement>> getAllEtablissement();

    @Query("select max(etabid) from etablissement_table")
    Integer getMaxEtablissement();

    @Query("select * from ETABLISSEMENT_TABLE,zone_table " +
            "where etablissement_table.localite=zone_table.zone_ht_id order by nom_Etablissement")
    LiveData<List<CompleteEtablissement>> getAllCompleteEtablissement();

    @Query("select * from ETABLISSEMENT_TABLE,zone_table " +
            "where etablissement_table.localite=zone_table.zone_ht_id " +
            "and etablissement_table.nom_Etablissement like '%' || :nom || '%'")
    LiveData<List<CompleteEtablissement>> getCompleteEtabsByNom(String nom);

    @Query("select * from etablissement_table where is_new_etab=0 and etablissement_table.localite like :localite ||'%' or nom_Etablissement = 'N''EST PAS DANS LA LISTE' order by nom_Etablissement")
    LiveData<List<Etablissement>> getEtabByLocalite(String localite);

    @Query("select new_contact_etab.contact_id,etablissement_table.nom_Etablissement,etablissement_table.adresse,etablissement_table.localite,etablissement_table.latitude,etablissement_table.longitude, etablissement_table.cree_par,etablissement_table.cree_le,etablissement_table.transfere_par,etablissement_table.transfere_le from new_contact_etab,etablissement_table where new_contact_etab.new_etab_id=etablissement_table.etabId and etablissement_table.is_new_etab=1")
    List<JoinNewEtabNewContact> getNewEtablissement();

    @Query("select new_contact_etab.contact_id,new_contact_etab.etab_id from new_contact_etab " +
            "where new_contact_etab.etab_id is not null " +
            "and new_contact_etab.sync_data=0")
    List<OldEtablissement> getOldEtab();

    @Query("update etablissement_table set is_new_etab=0 where etId is null and is_new_etab=1")
    void updateNewEtabsAfterSync();
}
