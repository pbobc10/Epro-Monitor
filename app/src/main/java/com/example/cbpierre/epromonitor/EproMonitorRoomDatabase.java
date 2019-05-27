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

import com.example.cbpierre.epromonitor.dao.ContactDao;
import com.example.cbpierre.epromonitor.dao.ContactEtablissementDao;
import com.example.cbpierre.epromonitor.dao.EtablissementDao;
import com.example.cbpierre.epromonitor.dao.ForceDao;
import com.example.cbpierre.epromonitor.dao.LoginDao;
import com.example.cbpierre.epromonitor.dao.NatureDao;
import com.example.cbpierre.epromonitor.dao.PostLoginDao;
import com.example.cbpierre.epromonitor.dao.SecteurDao;
import com.example.cbpierre.epromonitor.dao.SpecialiteDao;
import com.example.cbpierre.epromonitor.dao.TitreDao;
import com.example.cbpierre.epromonitor.dao.ZoneDao;
import com.example.cbpierre.epromonitor.models.Contact;
import com.example.cbpierre.epromonitor.models.ContactEtablissement;
import com.example.cbpierre.epromonitor.models.Etablissement;
import com.example.cbpierre.epromonitor.models.Force;
import com.example.cbpierre.epromonitor.models.Login;
import com.example.cbpierre.epromonitor.models.Nature;
import com.example.cbpierre.epromonitor.models.PostLogin;
import com.example.cbpierre.epromonitor.models.Secteur;
import com.example.cbpierre.epromonitor.models.Specialite;
import com.example.cbpierre.epromonitor.models.Titre;
import com.example.cbpierre.epromonitor.models.Zone;


@Database(entities = {Login.class, Contact.class, PostLogin.class, Titre.class, Nature.class, Secteur.class, Force.class, Specialite.class, Zone.class, Etablissement.class, ContactEtablissement.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class EproMonitorRoomDatabase extends RoomDatabase {

    private static volatile EproMonitorRoomDatabase INSTANCE;
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    // new PopulateDbAsync(INSTANCE).execute();
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

    // To repopulate the database whenever the app is started

    public abstract PostLoginDao postLoginDao();


    /*public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final LoginDao loginDao;

        public PopulateDbAsync(EproMonitorRoomDatabase db) {
            loginDao = db.loginDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Login login = new Login("Test ePM", "testepm","SAM-004","4837489328734890","");
            loginDao.insert(login);
            Log.d("test", "room4");
            return null;
        }
    }*/

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
