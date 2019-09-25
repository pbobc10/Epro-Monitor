package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.ChoiceContactGH;
import com.example.cbpierre.epromonitor.models.ContactVisite;

import java.util.List;

@Dao
public interface ContactVisiteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContactVisite(ContactVisite contactVisite);

    @Query("delete from contact_visite")
    void deleteContatVisite();

    @Query("select contact_visite.nom_ratio,contact_visite.con_id from contact_visite,commune_localite_contact\n" +
            "where contact_visite.con_id =commune_localite_contact.con_id\n" +
            "and contact_visite.specialite<>'NA'\n" +
            "and contact_visite.specialite=:specialite\n" +
            "order by contact_visite.nom_ratio")
    LiveData<List<ChoiceContactGH>> allChoiceContactGH(String specialite);

    @Query("select contact_visite.nom_ratio,contact_visite.con_id from contact_visite,commune_localite_contact\n" +
            "where contact_visite.con_id =commune_localite_contact.con_id\n" +
            "and contact_visite.specialite<>'NA'\n" +
            "and contact_visite.specialite=:specialite \n" +
            "and commune_localite_contact.commune like :commune||'%'\n" +
            "order by contact_visite.nom_ratio")
    LiveData<List<ChoiceContactGH>> allChoiceContactGH(String specialite, String commune);

    @Query("select contact_visite.nom_ratio,contact_visite.con_id from contact_visite,commune_localite_contact\n" +
            "where contact_visite.con_id =commune_localite_contact.con_id\n" +
            "and contact_visite.specialite<>'NA'\n" +
            "and contact_visite.specialite=:specialite \n" +
            "and commune_localite_contact.commune like :commune||'%'\n" +
            "and commune_localite_contact.localite like :localite||'%' \n" +
            "order by contact_visite.nom_ratio")
    LiveData<List<ChoiceContactGH>> allChoiceContactGH(String specialite, String commune, String localite);
}
/*

select contact_visite.nom_ratio,contact_visite.con_id from contact_visite,commune_localite_contact
where contact_visite.con_id =commune_localite_contact.con_id
and contact_visite.specialite<>"NA"
and contact_visite.specialite="INT"
order by contact_visite.nom_ratio

----
select contact_visite.nom_ratio,contact_visite.con_id from contact_visite,commune_localite_contact
where contact_visite.con_id =commune_localite_contact.con_id
and contact_visite.specialite<>"NA"
and contact_visite.specialite="INT"
and commune_localite_contact.commune like "Pétion-Ville"
order by contact_visite.nom_ratio

------
select contact_visite.nom_ratio,contact_visite.con_id from contact_visite,commune_localite_contact
where contact_visite.con_id =commune_localite_contact.con_id
and contact_visite.specialite<>"NA"
and contact_visite.specialite="INT"
and commune_localite_contact.commune like"Pétion-Ville%"
and commune_localite_contact.localite like"Frères%"
order by contact_visite.nom_ratio
 */
