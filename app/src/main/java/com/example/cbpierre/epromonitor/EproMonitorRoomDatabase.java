package com.example.cbpierre.epromonitor;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cbpierre.epromonitor.dao.AcceptabiliteDao;
import com.example.cbpierre.epromonitor.dao.CommuneLocaliteContactDao;
import com.example.cbpierre.epromonitor.dao.ContactDao;
import com.example.cbpierre.epromonitor.dao.ContactEtablissementDao;
import com.example.cbpierre.epromonitor.dao.ContactVisiteDao;
import com.example.cbpierre.epromonitor.dao.EtablissementDao;
import com.example.cbpierre.epromonitor.dao.ForceDao;
import com.example.cbpierre.epromonitor.dao.GHDao;
import com.example.cbpierre.epromonitor.dao.GHJourContactDao;
import com.example.cbpierre.epromonitor.dao.GHJourContactProduitDao;
import com.example.cbpierre.epromonitor.dao.GHJourDao;
import com.example.cbpierre.epromonitor.dao.LoginDao;
import com.example.cbpierre.epromonitor.dao.NatureDao;
import com.example.cbpierre.epromonitor.dao.NewContactEtabDao;
import com.example.cbpierre.epromonitor.dao.PaContactDao;
import com.example.cbpierre.epromonitor.dao.PaContactProduitDao;
import com.example.cbpierre.epromonitor.dao.PlanActionDao;
import com.example.cbpierre.epromonitor.dao.PostLoginDao;
import com.example.cbpierre.epromonitor.dao.ProduitDao;
import com.example.cbpierre.epromonitor.dao.SecteurDao;
import com.example.cbpierre.epromonitor.dao.SpecialiteDao;
import com.example.cbpierre.epromonitor.dao.StatutJourDao;
import com.example.cbpierre.epromonitor.dao.StatutVisiteDao;
import com.example.cbpierre.epromonitor.dao.TitreDao;
import com.example.cbpierre.epromonitor.dao.ZoneDao;
import com.example.cbpierre.epromonitor.models.AcceptabiliteRef;
import com.example.cbpierre.epromonitor.models.CommuneLocaliteContact;
import com.example.cbpierre.epromonitor.models.Contact;
import com.example.cbpierre.epromonitor.models.ContactEtablissement;
import com.example.cbpierre.epromonitor.models.ContactVisite;
import com.example.cbpierre.epromonitor.models.Etablissement;
import com.example.cbpierre.epromonitor.models.Force;
import com.example.cbpierre.epromonitor.models.GH;
import com.example.cbpierre.epromonitor.models.GHJour;
import com.example.cbpierre.epromonitor.models.GHJourContact;
import com.example.cbpierre.epromonitor.models.GHJourContactProduit;
import com.example.cbpierre.epromonitor.models.Login;
import com.example.cbpierre.epromonitor.models.Nature;
import com.example.cbpierre.epromonitor.models.NewContactETab;
import com.example.cbpierre.epromonitor.models.PaContact;
import com.example.cbpierre.epromonitor.models.PaContactProduit;
import com.example.cbpierre.epromonitor.models.PlanAction;
import com.example.cbpierre.epromonitor.models.PostLogin;
import com.example.cbpierre.epromonitor.models.Produit;
import com.example.cbpierre.epromonitor.models.Secteur;
import com.example.cbpierre.epromonitor.models.Specialite;
import com.example.cbpierre.epromonitor.models.StatutJourRef;
import com.example.cbpierre.epromonitor.models.StatutVisiteRef;
import com.example.cbpierre.epromonitor.models.Titre;
import com.example.cbpierre.epromonitor.models.Zone;


@Database(entities = {Login.class, Contact.class, PostLogin.class, Titre.class, Nature.class, Secteur.class, Force.class, Specialite.class, Zone.class, Etablissement.class, ContactEtablissement.class, NewContactETab.class,/*Plan D'action PA*/ PlanAction.class, PaContact.class, PaContactProduit.class, Produit.class, ContactVisite.class, GH.class, GHJour.class, GHJourContact.class, GHJourContactProduit.class, StatutVisiteRef.class, StatutJourRef.class, AcceptabiliteRef.class, CommuneLocaliteContact.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class EproMonitorRoomDatabase extends RoomDatabase {

    private static volatile EproMonitorRoomDatabase INSTANCE;
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                    Log.d("test", "room3");

                    //test to insert contact
                    // new InsertContactTask(INSTANCE).execute();
                }
            };

    public static EproMonitorRoomDatabase getDatabase(final Context context) {
        Log.d("test", "room1");
        if (INSTANCE == null) {
            synchronized (EproMonitorRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EproMonitorRoomDatabase.class, "word_database").addCallback(sRoomDatabaseCallback).build();
                    Log.d("test", "room2");
                }

            }
        }
        return INSTANCE;

    }

    public abstract TitreDao titreDao();

    public abstract ZoneDao zoneDao();

    public abstract ForceDao forceDao();

    public abstract SpecialiteDao specialiteDao();

    public abstract SecteurDao secteurDao();

    public abstract NatureDao natureDao();

    public abstract LoginDao loginDao();

    public abstract ContactDao contactDao();

    public abstract EtablissementDao etablissementDao();

    public abstract ContactEtablissementDao contactEtablissementDao();

    public abstract NewContactEtabDao newContactEtabDao();

    // To repopulate the database whenever the app is started

    public abstract PostLoginDao postLoginDao();

    /**
     * Plan d'Action  PA
     **/
    public abstract PlanActionDao planActionDao();

    public abstract PaContactDao paContactDao();

    public abstract PaContactProduitDao paContactProduitDao();

    public abstract ProduitDao produitDao();

    public abstract ContactVisiteDao contactVisiteDao();

    /**
     * GH
     */
    public abstract GHDao ghDao();

    public abstract GHJourDao ghJourDao();

    public abstract GHJourContactDao ghJourContactDao();

    public abstract GHJourContactProduitDao ghJourContactProduitDao();

    public abstract StatutJourDao statutJourDao();

    public abstract StatutVisiteDao statutVisiteDao();

    public abstract AcceptabiliteDao acceptabiliteDao();

    public abstract CommuneLocaliteContactDao communeLocaliteContactDao();


    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final EtablissementDao etablissementDao;
        private final ZoneDao zoneDao;
        private final NatureDao natureDao;
        private final TitreDao titreDao;
        private final SecteurDao secteurDao;
        private final SpecialiteDao specialiteDao;
        private final ForceDao forceDao;

        public PopulateDbAsync(EproMonitorRoomDatabase db) {
            etablissementDao = db.etablissementDao();
            zoneDao = db.zoneDao();
            natureDao = db.natureDao();
            titreDao = db.titreDao();
            secteurDao = db.secteurDao();
            specialiteDao = db.specialiteDao();
            forceDao = db.forceDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            //insert Etablissement
            Etablissement eta = new Etablissement(0, "N'EST PAS DANS LA LISTE", null, null, null, null, 0, false, null, null, null, null, null, null, false);
            etablissementDao.insert(eta);

            //insert Zone
            Zone zone = new Zone("AAA", "--SELECTIONNER UNE LOCALITE--", "AAA", "AAA", "AAA", "AAA");
            zoneDao.insert(zone);
            Log.d("test", "room4");

            //insert Nature
            Nature nature = new Nature("AA1", "-- SELECTIONNER UNE NATURE --", "A");
            natureDao.insert(nature);

            //insert Titre
            Titre titre = new Titre("AA2", "-- SELECTIONNER UN TITRE --", "A");
            titreDao.insert(titre);

            //insert Secteur
            Secteur secteur = new Secteur("AA3", "-- SELECTIONNER UN SECTEUR --", "A");
            secteurDao.insert(secteur);

            //insert Specialite
            Specialite specialite = new Specialite("AA4", "-- SELECTIONNER UNE SPECIALITE --", "A", "A");
            specialiteDao.insert(specialite);

            // insert Force
            Force force = new Force("AA5", "-- SELECTIONNER UNE FORCE --");
            forceDao.insert(force);
            return null;
        }
    }

    //test to insert contacts
   /* private static class InsertContactTask extends AsyncTask<Void, Void, Void> {

        private ContactDao mContact;

        public InsertContactTask(EproMonitorRoomDatabase db) {
            this.mContact = db.contactDao();
        }

        @Override
        protected Void doInBackground(Void... contacts) {
            Contact contact = new Contact(102, "test", "O'Black", "David", "NON APLLICABLE", "PRESCRIPTEUR", "OBSTETRIQUE ET GYNECOLOGIE",
                    "F1", "33515777", "33515777", "33515777", "oblack2012@gmail.com", 0, "BOB", null, "BOB", null, "BOB", null, 0, "BOB", null);

            mContact.insert(contact);
            return null;
        }
    }*/

}
